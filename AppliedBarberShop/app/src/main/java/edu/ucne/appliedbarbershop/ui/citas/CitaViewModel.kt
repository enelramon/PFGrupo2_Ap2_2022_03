package edu.ucne.appliedbarbershop.ui.citas

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.appliedbarbershop.data.local.models.*
import edu.ucne.appliedbarbershop.data.local.repository.CitaRepository
import edu.ucne.appliedbarbershop.data.remote.api_repository.CitaApiRepository
import edu.ucne.appliedbarbershop.data.remote.dto.CitaDto
import edu.ucne.appliedbarbershop.ui.navegacion.NavegacionViewModel
import edu.ucne.appliedbarbershop.utils.Screen
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject


@HiltViewModel
class CitaViewModel @Inject constructor(
    private val api: CitaApiRepository,
    private val citaRepository: CitaRepository
) : ViewModel() {

    var citaId by mutableStateOf(0)
    var servicioId by mutableStateOf(0)
    var barberoId by mutableStateOf(0)
    var clienteId by mutableStateOf(0)
    var fecha by mutableStateOf("")
    var mensaje by mutableStateOf("")
    var usuarioCreacionId by mutableStateOf(0)
    var usuarioModificacionId by mutableStateOf(0)
    var status by mutableStateOf(1)

    var enableSubmit by mutableStateOf(true)
    var openDialogRechazar by mutableStateOf(false)
    val instanciaBarbero = Barbero(
        barberoId = 0,
        nombre = "",
        apellido = "",
        celular = "",
        fechaNacimiento = "",
        imagen = "",
        status = 1
    )
    var barbero by mutableStateOf(
        instanciaBarbero
    )
    val instanciaServicio = Servicio(
        servicioId = 0,
        nombre = "",
        imagen = "",
        usuarioCreacionId = 0,
        usuarioModificacionId = 0,
        status = 1,
    )
    var servicio by mutableStateOf(
        instanciaServicio
    )
    var fch by mutableStateOf("")
    var hra by mutableStateOf("")

    fun onOpenDialogRechazarChange(t: Boolean) {
        openDialogRechazar = t
    }

    fun onCitaIdChange(t: Int) {
        citaId = t
    }

    fun onServicioIdChange(t: Int) {
        servicioId = t
    }

    fun onBarberoIdChange(t: Int) {
        barberoId = t
    }

    fun onClienteIdChange(t: Int) {
        clienteId = t
    }

    fun onFechaChange(t: String) {
        fecha = t
    }

    fun onFchChange(t: String) {
        fch = t
    }

    fun onHraChange(t: String) {
        hra = t
    }

    fun onUsuarioCreacionIdChange(t: Int) {
        usuarioCreacionId = t
    }

    fun onUsuarioModificacionIdChange(t: Int) {
        usuarioModificacionId = t
    }

    fun onStatusChange(t: Int) {
        status = t
    }

    fun buscarCita(id: Int, navegacionViewModel: NavegacionViewModel) {
        navegacionViewModel.citasPendientes.forEach { c ->
            if (c.citaId == id) {
                citaId = c.citaId
                servicioId = c.servicioId
                barberoId = c.barberoId
                clienteId = c.clienteId
                fecha = c.fecha
                mensaje = c.mensaje ?: ""
                usuarioCreacionId = c.usuarioCreacionId
                usuarioModificacionId = c.usuarioModificacionId ?: 0
                status = c.status

                var localDateTime = LocalDateTime.parse(c.fecha)

                fch =
                    localDateTime.year.toString() + "-" + (if (localDateTime.monthValue.toString()
                            .count() == 1
                    ) "0" + localDateTime.monthValue.toString() else localDateTime.monthValue.toString()) + "-" + (if (localDateTime.dayOfMonth.toString()
                            .count() == 1
                    ) "0" + localDateTime.dayOfMonth.toString() else localDateTime.dayOfMonth.toString())
                hra =
                    (if (localDateTime.hour.toString()
                            .count() == 1
                    ) "0" + localDateTime.hour.toString() else localDateTime.hour.toString()) + ":" + (if (localDateTime.minute.toString()
                            .count() == 1
                    ) "0" + localDateTime.minute.toString() else localDateTime.minute.toString()) + ":" + (if (localDateTime.second.toString()
                            .count() == 1
                    ) "0" + localDateTime.second.toString() else localDateTime.second.toString())

                navegacionViewModel.barberos.forEach {
                    if (it.barberoId == c.barberoId)
                        barbero = it
                }

                navegacionViewModel.servicios.forEach {
                    if (it.servicioId == c.servicioId)
                        servicio = it
                }
            }
        }
    }

    fun cargarCita(id: Int, navegacionViewModel: NavegacionViewModel) {
        viewModelScope.launch {
            citaRepository.getById(id).collect { c ->
                if (c != null) {
                    citaId = c.citaId
                    servicioId = c.servicioId
                    barberoId = c.barberoId
                    clienteId = c.clienteId
                    fecha = c.fecha
                    mensaje = c.mensaje ?: ""
                    usuarioCreacionId = c.usuarioCreacionId
                    usuarioModificacionId = c.usuarioModificacionId ?: 0
                    status = c.status

                    var localDateTime = LocalDateTime.parse(c.fecha)

                    fch =
                        localDateTime.year.toString() + "-" + (if (localDateTime.monthValue.toString()
                                .count() == 1
                        ) "0" + localDateTime.monthValue.toString() else localDateTime.monthValue.toString()) + "-" + (if (localDateTime.dayOfMonth.toString()
                                .count() == 1
                        ) "0" + localDateTime.dayOfMonth.toString() else localDateTime.dayOfMonth.toString())
                    hra =
                        (if (localDateTime.hour.toString()
                                .count() == 1
                        ) "0" + localDateTime.hour.toString() else localDateTime.hour.toString()) + ":" + (if (localDateTime.minute.toString()
                                .count() == 1
                        ) "0" + localDateTime.minute.toString() else localDateTime.minute.toString()) + ":" + (if (localDateTime.second.toString()
                                .count() == 1
                        ) "0" + localDateTime.second.toString() else localDateTime.second.toString())

                    navegacionViewModel.barberos.forEach {
                        if (it.barberoId == c.barberoId)
                            barbero = it
                    }

                    navegacionViewModel.servicios.forEach {
                        if (it.servicioId == c.servicioId)
                            servicio = it
                    }
                }
            }
        }
    }

    fun eligeBarbero(bar: Barbero) {
        if (barbero == bar)
            barbero = instanciaBarbero
        else
            barbero = bar
    }

    fun eligeServicio(ser: Servicio) {
        if (servicio == ser)
            servicio = instanciaServicio
        else
            servicio = ser
    }

    fun realizar(
        id: Int,
        localContext: Context,
        navController: NavController,
        navegacionViewModel: NavegacionViewModel
    ) {
        buscarCita(id, navegacionViewModel)
        val s = 2
        save(
            localContext = localContext,
            navController = navController,
            navegacionViewModel = navegacionViewModel,
            statusCita = s,
            salirAlTerminar = false
        )
    }

    fun rechazar(
        localContext: Context,
        navController: NavController,
        navegacionViewModel: NavegacionViewModel
    ) {
        val s = 3
        save(
            localContext = localContext,
            navController = navController,
            navegacionViewModel = navegacionViewModel,
            statusCita = s,
            salirAlTerminar = false
        )
    }

    fun save(
        localContext: Context,
        navController: NavController,
        navegacionViewModel: NavegacionViewModel,
        statusCita: Int = 1,
        salirAlTerminar: Boolean = true
    ) {
        enableSubmit = false

        servicioId = servicio.servicioId
        barberoId = barbero.barberoId
        clienteId = navegacionViewModel.cliente.clienteId
        fecha = fch + "T" + hra
        status = statusCita

        if (citaId == 0)
            usuarioCreacionId = navegacionViewModel.cliente.clienteId

        usuarioModificacionId = navegacionViewModel.cliente.clienteId

        viewModelScope.launch {
            val intentoGuardar = if (citaId == 0) api.insertCita(
                CitaDto(
                    citaId = citaId.toInt(),
                    servicioId = servicioId,
                    barberoId = barberoId,
                    clienteId = clienteId,
                    fecha = fecha,
                    mensaje = mensaje,
                    usuarioCreacionId = usuarioCreacionId,
                    usuarioModificacionId = usuarioModificacionId,
                    status = status
                )
            ) else api.updateCita(
                citaId.toString(),
                CitaDto(
                    citaId = citaId.toInt(),
                    servicioId = servicioId,
                    barberoId = barberoId,
                    clienteId = clienteId,
                    fecha = fecha,
                    mensaje = mensaje,
                    usuarioCreacionId = usuarioCreacionId,
                    usuarioModificacionId = usuarioModificacionId,
                    status = status
                )
            )

            if (
                intentoGuardar.citaId > 0 && validacion()
            ) {
                var citaDb = Cita(
                    citaId = intentoGuardar.citaId.toInt(),
                    servicioId = intentoGuardar.servicioId,
                    barberoId = intentoGuardar.barberoId,
                    clienteId = intentoGuardar.clienteId,
                    fecha = intentoGuardar.fecha,
                    mensaje = intentoGuardar.mensaje,
                    usuarioCreacionId = intentoGuardar.usuarioCreacionId,
                    usuarioModificacionId = intentoGuardar.usuarioModificacionId,
                    status = intentoGuardar.status
                )

                citaRepository.insert(citaDb)

                if (true) {
                    navegacionViewModel.sincronizarCitasApi()
                    if (salirAlTerminar)
                        navController.navigate(Screen.ConfirmaRegistroCitaScreen.Route)
                } else {
                    Toast.makeText(localContext, "No se pudo guardar!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(localContext, msg, Toast.LENGTH_SHORT).show()
            }
            enableSubmit = true
        }
    }
    var msg = ""

    fun validacion(): Boolean {

        val delim = "-"
        var fechaActual = LocalDateTime.now()
        val fechaElegida = fch.split(delim)

        if(fechaElegida[0].toInt() < fechaActual.year.toString().toInt())
        {
            msg = "No se pueden agendar citas con fechas menor a la de hoy"
            return false
        }else if(fechaElegida[1].toInt() < fechaActual.monthValue.toString().toInt())
        {
            msg = "No se pueden agendar citas con fechas menor a la de hoy"
            return false
        }else if(fechaElegida[2].toInt() < fechaActual.dayOfMonth.toString().toInt())
        {
            msg = "No se pueden agendar citas con fechas menor a la de hoy"
            return false
        }

        return true
    }

    fun update(id: String, cita: CitaDto) {
        viewModelScope.launch {
            api.updateCita(id, cita)
        }
    }

    fun searchById(id: String?) {
        viewModelScope.launch {
            api.getCita(id) // se debe igualar a las variables de los campos en el registro
        }
    }

    fun save(cita: CitaDto) {
        viewModelScope.launch {
            api.insertCita(cita)
        }
    }

    fun delete(cita: CitaDto) {
        viewModelScope.launch {
            api.deleteCita(cita.citaId.toString())
        }
    }

}