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
import edu.ucne.appliedbarbershop.data.local.repository.BarberoRepository
import edu.ucne.appliedbarbershop.data.local.repository.CitaRepository
import edu.ucne.appliedbarbershop.data.local.repository.ClienteRepository
import edu.ucne.appliedbarbershop.data.local.repository.ServicioRepository
import edu.ucne.appliedbarbershop.data.remote.api_repository.BarberoApiRepository
import edu.ucne.appliedbarbershop.data.remote.api_repository.CitaApiRepository
import edu.ucne.appliedbarbershop.data.remote.api_repository.ServicioApiRepository
import edu.ucne.appliedbarbershop.utils.Constantes
import edu.ucne.appliedbarbershop.utils.Screen
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavegacionViewModel @Inject constructor(
    private val clienteRepository: ClienteRepository,
    private val servicioRepository: ServicioRepository,
    private val servicioApiRepository: ServicioApiRepository,
    private val citaRepository: CitaRepository,
    private val citaApiRepository: CitaApiRepository,
    private val barberoRepository: BarberoRepository,
    private val barberoApiRepository: BarberoApiRepository,
    private val dataBase: AppDataBase
) : ViewModel() {
    var servicios by mutableStateOf(emptyList<Servicio>())
    var citas by mutableStateOf(emptyList<CitaCompleta>())
    var clientes by mutableStateOf(emptyList<Cliente>())
    var barberos by mutableStateOf(emptyList<Barbero>())

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

    val items = mutableListOf(
        ItemNav(
            descripcion = "Principal",
            icono = Icons.Default.Home,
            screen = Screen.PrincipalScreen,
            titulo = Constantes.NombreEmpresa.value
        )
    )

    fun sincronizarCliente() {
        viewModelScope.launch {
            var clientesDb = emptyList<Cliente>()
            clienteRepository.getAll().collect {
                clientesDb = it
                if (clientesDb.count() > 0) {
                    clientes = clientesDb
                    cliente = clientes.first()
                    sincronizarCitas(cliente.clienteId)
                }
            }
        }
    }

    fun sincronizarServicios() {
        viewModelScope.launch {
            var serviciosApi = servicioApiRepository.getServicios()
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
            var serviciosDb = emptyList<Servicio>()
            servicioRepository.getAll().collect {
                serviciosDb = it
                if (serviciosDb.count() > 0) {
                    servicios = serviciosDb
                }
            }
        }
    }

    fun sincronizarBarberos() {
        viewModelScope.launch {
            var barberosApi = barberoApiRepository.getBarberos()
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
            var barberosDb = emptyList<Barbero>()
            barberoRepository.getAll().collect {
                barberosDb = it
                if (barberosDb.count() > 0) {
                    barberos = barberosDb
                }
            }
        }
    }

    fun sincronizarCitas(id: Int) {
        viewModelScope.launch {
            var citasApi = citaApiRepository.getCitasByClienteId(id.toString())
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
            var citasDb = emptyList<CitaCompleta>()
            citaRepository.getAll().collect {
                citasDb = it
                if (citasDb.count() > 0) {
                    citas = citasDb
                }
            }
        }
    }

    init {
        sincronizarServicios()
        sincronizarBarberos()
        // TODO agregar la condicion de si es barbero
        if (true) {
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
        items.addAll(
            mutableListOf(
                ItemNav(
                    descripcion = "Mis Citas",
                    icono = Icons.Default.EditCalendar,
                    screen = Screen.ConsultaMisCitasScreen
                ),
                ItemNav(
                    descripcion = "Mis Perfiles",
                    icono = Icons.Default.Person,
                    screen = Screen.ConsultaMisClientesScreen
                ),
                ItemNav(
                    descripcion = "Ajustes",
                    icono = Icons.Default.Settings,
                    screen = Screen.AjustesScreen
                ),
                ItemNav(
                    descripcion = "Sobre Nosotros",
                    icono = Icons.Default.HelpOutline,
                    screen = Screen.IntroScreen
                )
            )
        )
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