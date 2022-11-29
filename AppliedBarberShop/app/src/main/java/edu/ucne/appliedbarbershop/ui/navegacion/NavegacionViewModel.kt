package edu.ucne.appliedbarbershop.ui.navegacion

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.appliedbarbershop.data.local.AppDataBase
import edu.ucne.appliedbarbershop.data.local.models.*
import edu.ucne.appliedbarbershop.data.local.repository.*
import edu.ucne.appliedbarbershop.data.remote.api_repository.BarberoApiRepository
import edu.ucne.appliedbarbershop.data.remote.api_repository.CitaApiRepository
import edu.ucne.appliedbarbershop.data.remote.api_repository.ClienteApiRepository
import edu.ucne.appliedbarbershop.data.remote.api_repository.ServicioApiRepository
import edu.ucne.appliedbarbershop.data.remote.dto.BarberoDto
import edu.ucne.appliedbarbershop.data.remote.dto.CitaDto
import edu.ucne.appliedbarbershop.data.remote.dto.ClienteDto
import edu.ucne.appliedbarbershop.data.remote.dto.ServicioDto
import edu.ucne.appliedbarbershop.utils.Constantes
import edu.ucne.appliedbarbershop.utils.Screen
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavegacionViewModel @Inject constructor(
    private val clienteRepository: ClienteRepository,
    private val servicioRepository: ServicioRepository,
    private val citaRepository: CitaRepository,
    private val entornoRepository: EntornoRepository,
    private val clienteApiRepository: ClienteApiRepository,
    private val servicioApiRepository: ServicioApiRepository,
    private val citaApiRepository: CitaApiRepository,
    private val barberoRepository: BarberoRepository,
    private val barberoApiRepository: BarberoApiRepository,
    private val perfilRepository: PerfilRepository,
    private val dataBase: AppDataBase
) : ViewModel() {
    var servicios by mutableStateOf(emptyList<Servicio>())
    var citas by mutableStateOf(emptyList<CitaCompleta>())
    var citasPendientes by mutableStateOf(emptyList<CitaCompleta>())
    var todasCitas by mutableStateOf(emptyList<CitaCompleta>())
    var clientes by mutableStateOf(emptyList<Cliente>())
    var barberos by mutableStateOf(emptyList<Barbero>())
    var perfiles by mutableStateOf(emptyList<PerfilCompleto>())
    var perfilId by mutableStateOf(0)
    var isBarberoAutenticado by mutableStateOf(false)
    var sincronizacionCliente by mutableStateOf(false)
    var sincronizacionServicios by mutableStateOf(false)
    var sincronizacionBarberos by mutableStateOf(false)
    var sincronizacionCitas by mutableStateOf(false)
    var sincronizacionFinalizada by mutableStateOf(true)

    var cliente by mutableStateOf(
        Cliente(
            clienteId = 0,
            nombre = "",
            apellido = "",
            celular = "",
            fechaNacimiento = "",
            imagen = "",
            status = 1
        )
    )

    val items = mutableListOf<ItemNav>(
        ItemNav(
            descripcion = "Principal",
            icono = Icons.Default.Home,
            screen = Screen.PrincipalScreen,
            titulo = Constantes.NombreEmpresa.value
        )
    )

    fun sincronizarEntorno() {
        viewModelScope.launch {
            entornoRepository.getEntorno().collect {
                if (it != null) {
                    perfilId = it.clienteIdSeleccionado
                    isBarberoAutenticado = it.isBarberoAutenticado
                } else {
                    perfilId = 0
                    entornoRepository.insert(
                        Entorno(
                            entornoId = 1,
                            codigoBarberia = "244466666",
                            clienteIdSeleccionado = perfilId,
                            isBarberoAutenticado = false
                        )
                    )
                }

                val q = async(start = CoroutineStart.LAZY) { sincronizarClientes() }
                val w = async(start = CoroutineStart.LAZY) { sincronizarServicios() }
                val e = async(start = CoroutineStart.LAZY) { sincronizarBarberos() }
                val r = async(start = CoroutineStart.LAZY) { sincronizarCitas(perfilId) }
                val t = async(start = CoroutineStart.LAZY) { sincronizarPerfiles() }
                val y = async(start = CoroutineStart.LAZY) { sincronizarCitasApi() }
                val u = async(start = CoroutineStart.LAZY) { sincronizarClientesApi() }
                val i = async(start = CoroutineStart.LAZY) { sincronizarServiciosApi() }
                val o = async(start = CoroutineStart.LAZY) { sincronizarBarberosApi() }

                q.await()
                w.await()
                e.await()
                r.start()
                t.start()
                y.start()
                u.start()
                i.start()
                o.start()

                generarNav()
            }
        }
    }

    fun generarNav() {
        items.clear()

        items.add(
            ItemNav(
                descripcion = "Principal",
                icono = Icons.Default.Home,
                screen = Screen.PrincipalScreen,
                titulo = Constantes.NombreEmpresa.value
            )
        )

        if (isBarberoAutenticado) {
            items.addAll(
                mutableListOf(
                    ItemNav(
                        descripcion = "Citas Pendientes",
                        icono = Icons.Default.Schedule,
                        screen = Screen.ConsultaCitasPendientesScreen
                    ),
                    ItemNav(
                        descripcion = "Historial de Citas",
                        icono = Icons.Default.EventAvailable,
                        screen = Screen.ConsultaHistorialCitasScreen
                    ),
                    ItemNav(
                        descripcion = "Servicios",
                        icono = Icons.Default.Favorite,
                        screen = Screen.ConsultaServiciosScreen
                    )
                )
            )
        }

        items.add(
            ItemNav(
                descripcion = "Mis Citas",
                icono = Icons.Default.EditCalendar,
                screen = Screen.ConsultaMisCitasScreen
            )
        )

        if (!isBarberoAutenticado) {
            items.add(
                ItemNav(
                    descripcion = "Mis Perfiles",
                    icono = Icons.Default.Person,
                    screen = Screen.ConsultaMisClientesScreen
                )
            )
        }

        items.add(
            ItemNav(
                descripcion = "Ajustes",
                icono = Icons.Default.Settings,
                screen = Screen.AjustesScreen
            )
        )
    }

    fun sincronizarPerfiles() {
        viewModelScope.launch {
            var perfilesDb = emptyList<PerfilCompleto>()
            perfilRepository.getAll().collect {
                perfilesDb = it
                if (perfilesDb.count() > 0) {
                    perfiles = perfilesDb
//                    perfilesDb.forEach {
//                        if (it.perfilId == perfilId) {
//                            perfil = it
//                            return@forEach
//                        }
//                    }
                }
            }
        }
    }

    fun sincronizarClientesApi() {
        viewModelScope.launch {
            var clientesApi = clienteApiRepository.getClientes()

            if (clientesApi.count() > 0)
                clienteRepository.truncateTable()

            clientesApi.forEach {
                dataBase.clienteDao.insert(
                    Cliente(
                        it.clienteId,
                        it.nombre,
                        it.apellido,
                        it.celular,
                        it.fechaNacimiento,
                        it.imagen,
                        it.status
                    )
                )
            }
            sincronizarClientes()
        }
    }

    fun sincronizarClientes() {
        viewModelScope.launch {
            var clientesDb = emptyList<Cliente>()
            clienteRepository.getAll().collect {
                clientesDb = it
                if (clientesDb.count() > 0) {
                    clientes = clientesDb
                    clientesDb.forEach {
                        if (it.clienteId == perfilId) {
                            cliente = it
                            return@forEach
                        }
                    }
                }
                sincronizacionCliente = true
            }
        }
    }

    fun sincronizarServiciosApi() {
        viewModelScope.launch {
            var serviciosApi = servicioApiRepository.getServicios()

            if (serviciosApi.count() > 0)
                dataBase.servicioDao.truncateTable()

            serviciosApi.forEach {
                dataBase.servicioDao.insert(
                    Servicio(
                        it.servicioId,
                        it.nombre,
                        it.imagen,
                        it.usuarioCreacionId,
                        it.usuarioModificacionId,
                        it.status
                    )
                )
            }
            sincronizarServicios()
        }
    }

    fun sincronizarServicios() {
        viewModelScope.launch {
            var serviciosDb = emptyList<Servicio>()
            servicioRepository.getAll().collect {
                serviciosDb = it
                if (serviciosDb.count() > 0) {
                    servicios = serviciosDb
                }
            }
        }
    }

    fun sincronizarBarberosApi() {
        viewModelScope.launch {
            var barberosApi = barberoApiRepository.getBarberos()

            if (barberosApi.count() > 0)
                dataBase.barberoDao.truncateTable()

            barberosApi.forEach {
                dataBase.barberoDao.insert(
                    Barbero(
                        it.barberoId,
                        it.nombre,
                        it.apellido,
                        it.celular,
                        it.fechaNacimiento,
                        it.imagen,
                        it.status
                    )
                )
            }
            sincronizarBarberos()
        }
    }

    fun sincronizarBarberos() {
        viewModelScope.launch {
            var barberosDb = emptyList<Barbero>()
            barberoRepository.getAll().collect {
                barberosDb = it
                if (barberosDb.count() > 0) {
                    barberos = barberosDb
                }
            }
        }
    }

    fun sincronizarCitasApi() {
        viewModelScope.launch {

            var citasApi = citaApiRepository.getCitas()

            if (citasApi.count() > 0)
                dataBase.citaDao.truncateTable()

            citasApi.forEach {
                dataBase.citaDao.insert(
                    Cita(
                        it.citaId,
                        it.servicioId,
                        it.barberoId,
                        it.clienteId,
                        it.fecha,
                        it.mensaje,
                        it.usuarioCreacionId,
                        it.usuarioModificacionId,
                        it.status
                    )
                )
            }
            sincronizarCitas(perfilId)
        }
    }

    fun sincronizarCitas(id: Int) {
        viewModelScope.launch {
            var citasDb = emptyList<CitaCompleta>()
            citaRepository.getAll().collect {
                citasDb = it
                if (citasDb.count() > 0) {
                    todasCitas = citasDb

                    var tmpCitas = mutableListOf<CitaCompleta>()
                    var tmpCitasPendientes = mutableListOf<CitaCompleta>()
                    citasDb.forEach {
                        if (it.clienteId == id)
                            tmpCitas.add(it)

                        if (it.status == 1)
                            tmpCitasPendientes.add(it)
                    }
                    citas = tmpCitas
                    citasPendientes = tmpCitasPendientes
                }
            }
        }
    }

    init {
        sincronizarEntorno()
    }

    var selectedItem by mutableStateOf(items[0])
    var mostrarNav by mutableStateOf(false)

    var startDestination = Screen.IntroScreen.Route

    fun onChangeSelectedItem(itemNav: ItemNav) {
        selectedItem = itemNav
    }

    fun onChangeMostrarNav(t: Boolean) {
        if (!t) selectedItem = items.get(0)
        mostrarNav = t
    }

}

class ItemNav(
    val descripcion: String,
    val icono: ImageVector,
    val screen: Screen,
    val titulo: String = descripcion
)