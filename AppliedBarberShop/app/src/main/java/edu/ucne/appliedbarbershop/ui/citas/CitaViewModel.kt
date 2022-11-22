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
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okio.IOException
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
    var status by mutableStateOf(0)

    fun onCitaIdChange(t: Int) { citaId = t }
    fun onServicioIdChange(t: Int) { servicioId = t }
    fun onBarberoIdChange(t: Int) { barberoId = t }
    fun onClienteIdChange(t: Int) { clienteId = t }
    fun onFechaChange(t: String) { fecha = t }
    fun onFchChange(t: String) { fch = t }
    fun onHraChange(t: String) { hra = t }

    fun onUsuarioCreacionIdChange(t: Int) { usuarioCreacionId = t }
    fun onUsuarioModificacionIdChange(t: Int) { usuarioModificacionId = t }
    fun onStatusChange(t: Int) { status = t }

    var enableSubmit by mutableStateOf(true)

    var citas by mutableStateOf(emptyList<CitaCompleta>())
    var barbero by mutableStateOf(Barbero(
        barberoId = 0,
        nombre = "",
        apellido = "",
        celular = "",
        fechaNacimiento = "",
        imagen = "",
        status = 1
    ))
    var servicio by mutableStateOf(Servicio(
        servicioId = 0,
        nombre = "",
        imagen = "",
        usuarioCreacionId = 0,
        usuarioModificacionId = 0,
        status = 1,
    ))
    var fch by mutableStateOf("")
    var hra by mutableStateOf("")

    fun eligeBarbero(bar: Barbero){ barbero = bar }
    fun eligeServicio(ser: Servicio){ servicio = ser }

    init {
        getCitas()
    }

    fun save(
        localContext: Context,
        navController: NavController,
        navegacionViewModel: NavegacionViewModel
    ) {
        enableSubmit = false
        viewModelScope.launch {
            val intentoGuardar = api.insertCita(
                CitaDto(
                    citaId = citaId.toInt(),
                    servicioId = servicioId,
                    barberoId = barberoId,
                    clienteId = clienteId,
                    fecha = fecha,
                    mensaje = mensaje,
                    usuarioCreacionId = 0,
                    usuarioModificacionId = 0,
                    status = status
                )
            )
            if (
                intentoGuardar.citaId > 0
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
                    navController.navigate(Screen.ConfirmaRegistroCitaScreen.Route)
                } else {
                    Toast.makeText(localContext, "No se pudo guardar!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(localContext, "No se pudo guardar!", Toast.LENGTH_SHORT).show()
            }
            enableSubmit = true
        }
    }

    fun getCitas(){
        viewModelScope.launch {
            citaRepository.getAll().collect {
                citas = it
            }
        }
    }

    fun update(id: String, cita: CitaDto){
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