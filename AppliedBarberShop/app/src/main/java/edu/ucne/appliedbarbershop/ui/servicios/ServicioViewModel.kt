package edu.ucne.appliedbarbershop.ui.servicios

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.appliedbarbershop.data.local.AppDataBase
import edu.ucne.appliedbarbershop.data.local.models.Barbero
import edu.ucne.appliedbarbershop.data.local.models.Cita
import edu.ucne.appliedbarbershop.data.local.models.Servicio
import edu.ucne.appliedbarbershop.data.local.repository.ServicioRepository
import edu.ucne.appliedbarbershop.data.remote.api_repository.ServicioApiRepository
import edu.ucne.appliedbarbershop.data.remote.dto.CitaDto
import edu.ucne.appliedbarbershop.data.remote.dto.ServicioDto
import edu.ucne.appliedbarbershop.ui.navegacion.NavegacionViewModel
import edu.ucne.appliedbarbershop.utils.Screen
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okio.IOException
import javax.inject.Inject

@HiltViewModel
class ServicioViewModel @Inject constructor(
    private val api: ServicioApiRepository,
    private val servicioRepository: ServicioRepository
) : ViewModel() {

    var servicioId by mutableStateOf(0)
    var nombre by mutableStateOf("")
    var imagen by mutableStateOf("")
    var usuarioCreacionId by mutableStateOf(0)
    var usuarioModificacionId by mutableStateOf(0)
    var status by mutableStateOf(0)

    var enableSubmit by mutableStateOf(true)

    fun onNombreChange(t: String) {
        nombre = t
    }

    fun onImagenChange(t: String) {
        imagen = t
    }

    var isErrorNombre = false
    var msgNombre = ""

    fun save(
        localContext: Context,
        navController: NavController,
        navegacionViewModel: NavegacionViewModel,
        salirAlTerminar: Boolean = true
    ) {
        enableSubmit = false

        if (servicioId == 0)
            usuarioCreacionId = navegacionViewModel.cliente.clienteId

        usuarioModificacionId = navegacionViewModel.cliente.clienteId

        viewModelScope.launch {
            val intentoGuardar = if (servicioId == 0) api.insertServicio(
                ServicioDto(
                    servicioId = servicioId,
                    nombre = nombre,
                    imagen = imagen,
                    usuarioCreacionId = usuarioCreacionId,
                    usuarioModificacionId = usuarioModificacionId,
                    status = status
                )

            ) else api.updateServicio(
                servicioId.toString(),
                ServicioDto(
                    servicioId = servicioId,
                    nombre = nombre,
                    imagen = imagen,
                    usuarioCreacionId = usuarioCreacionId,
                    usuarioModificacionId = usuarioModificacionId,
                    status = status
                )
            )
            if (
                intentoGuardar.servicioId > 0
            ) {
                var servicioDb = Servicio(
                    servicioId = intentoGuardar.servicioId,
                    nombre = intentoGuardar.nombre,
                    imagen = intentoGuardar.imagen,
                    usuarioCreacionId = intentoGuardar.usuarioCreacionId,
                    usuarioModificacionId = intentoGuardar.usuarioModificacionId,
                    status = intentoGuardar.status
                )
                servicioRepository.insert(servicioDb)

                if (true) {
                    navegacionViewModel.sincronizarServiciosApi()
                    if (salirAlTerminar)
                        navController.navigate(Screen.ConsultaServiciosScreen.Route)
                } else {
                    Toast.makeText(localContext, "No se pudo guardar!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(localContext, "No se pudo guardar!", Toast.LENGTH_SHORT).show()
            }
            enableSubmit = true
        }
    }

    fun getServicioByStatus() {
        viewModelScope.launch {
            api.getAllServiciosStatus()
        }
    }

    fun update(id: String, servicio: ServicioDto) {
        viewModelScope.launch {
            api.updateServicio(id, servicio)
        }
    }

    fun searchById(id: String?) {
        viewModelScope.launch {
            api.getServicio(
                id ?: ""
            ) // se debe igualar a las variables de los campos en el registro
        }
    }

    fun delete(servicio: ServicioDto) {
        viewModelScope.launch {
            api.deleteServicio(servicio.servicioId.toString())
        }
    }

    fun validar(): Boolean {
        if (nombre.isBlank()) {
            isErrorNombre = true
            msgNombre = "*Campo Obligatorio*"
        } else if (nombre.isDigitsOnly()) {
            isErrorNombre = true
            msgNombre = "*Descripcion invalidad(Solo puede contener letras)*"
        } else if (nombre.length in 1..4) {
            isErrorNombre = true
            msgNombre = "*La descripcion debe contener minimo(5) Caracteres*"
        } else {
            isErrorNombre = false
            msgNombre = ""
        }

        return isErrorNombre
    }
}