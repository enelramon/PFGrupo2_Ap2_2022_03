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
import java.time.LocalDateTime
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
    var fechaCreacion by mutableStateOf("")
    var fechaModificacion by mutableStateOf("")
    var status by mutableStateOf(1)

    var enableSubmit by mutableStateOf(true)

    fun onNombreChange(t: String) {
        nombre = t
    }

    fun onImagenChange(t: String) {
        imagen = t
    }

    fun cargarServicio(id: Int, navegacionViewModel: NavegacionViewModel) {
        viewModelScope.launch {
            servicioRepository.getById(id).collect { c ->
                if (c != null) {
                    servicioId = c.servicioId
                    nombre = c.nombre
                    imagen = c.imagen ?: ""
                    usuarioCreacionId = c.usuarioCreacionId
                    usuarioModificacionId = c.usuarioModificacionId ?: 0
                    status = c.status
                }
            }
        }
    }

    fun save(
        localContext: Context,
        navController: NavController,
        navegacionViewModel: NavegacionViewModel,
        salirAlTerminar: Boolean = true
    ) {
        enableSubmit = false

        if (servicioId == 0) {
            usuarioCreacionId = navegacionViewModel.cliente.clienteId
            fechaCreacion = LocalDateTime.now().toString()
        }

        fechaModificacion = LocalDateTime.now().toString()
        usuarioModificacionId = navegacionViewModel.cliente.clienteId


        viewModelScope.launch {
            if (validacion()) {
                val intentoGuardar = if (servicioId == 0) api.insertServicio(
                    ServicioDto(
                        servicioId = servicioId,
                        nombre = nombre,
                        imagen = imagen,
                        usuarioCreacionId = usuarioCreacionId,
                        usuarioModificacionId = usuarioModificacionId,
                        fechaCreacion = fechaCreacion,
                        fechaModificacion = fechaModificacion,
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
                        fechaCreacion = fechaCreacion,
                        fechaModificacion = fechaModificacion,
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
                        Toast.makeText(localContext, "No se pudo guardar!", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    Toast.makeText(localContext, msg, Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(localContext, msg, Toast.LENGTH_SHORT).show()
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

    var msg = ""

    fun validacion(): Boolean {
        if (nombre.isBlank()) {
            msg = "*Campo Obligatorio*"
            return false
        } else if (nombre.isDigitsOnly()) {
            msg = "*Descripcion invalida (Solo puede contener letras)*"
            return false
        } else if (nombre.length in 1..4) {
            msg = "*La descripcion debe contener minimo(5) Caracteres*"
            return false
        }

        return true
    }
}