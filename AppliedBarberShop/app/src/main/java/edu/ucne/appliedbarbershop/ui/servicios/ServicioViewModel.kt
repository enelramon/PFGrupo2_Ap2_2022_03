package edu.ucne.appliedbarbershop.ui.servicios

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.appliedbarbershop.data.local.AppDataBase
import edu.ucne.appliedbarbershop.data.local.models.Servicio
import edu.ucne.appliedbarbershop.data.local.repository.ServicioRepository
import edu.ucne.appliedbarbershop.data.remote.api_repository.ServicioApiRepository
import edu.ucne.appliedbarbershop.data.remote.dto.ServicioDto
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okio.IOException
import javax.inject.Inject

@HiltViewModel
class ServicioViewModel @Inject constructor(
    private val api: ServicioApiRepository,
    private val servicioRepository: ServicioRepository
) : ViewModel() {

    var currentId by mutableStateOf("")
    var nombre by mutableStateOf("")
    var imagen by mutableStateOf("")
    var usuarioCreacionId by mutableStateOf("")
    var usuarioModificacionId by mutableStateOf("")
    var status by mutableStateOf("")

    fun onNombreChange(t: String) {
        nombre = t
    }

    fun onImagenChange(t: String) {
        imagen = t
    }

    var servicios by mutableStateOf(servicioRepository.getAll())

    var isErrorNombre = false
    var msgNombre = ""

    fun searchById(id: String?) {
        viewModelScope.launch {
            api.getServicio(id ?: "")
        }
    }

    fun save(servicio: ServicioDto) {
        viewModelScope.launch {
            api.insertServicio(servicio)
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