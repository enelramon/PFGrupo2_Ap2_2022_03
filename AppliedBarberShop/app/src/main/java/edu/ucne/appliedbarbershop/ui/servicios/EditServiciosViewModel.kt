package edu.ucne.appliedbarbershop.ui.servicios

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.appliedbarbershop.data.remote.api_repository.ServicioApiRepository
import edu.ucne.appliedbarbershop.data.remote.dto.ServicioDto
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditServiciosViewModel @Inject constructor(
    private val api: ServicioApiRepository
): ViewModel() {

    var currentId by mutableStateOf("")

    val nombre by mutableStateOf("")
    val imagen by mutableStateOf("")
    val usuarioCreacionId by mutableStateOf("")
    val usuarioModificacionId by mutableStateOf("")
    val status by mutableStateOf("")

    init {
        searchById("1")
    }

    fun searchById(id: String?){
        viewModelScope.launch {
            api.getServicio(id ?: "")
        }
    }

    fun save(servicio: ServicioDto){
        viewModelScope.launch {
            api.insertServicio(servicio)
        }
    }

    var isErrorNombre = false
    var msgNombre = ""

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
        }
        else{
            isErrorNombre = false
            msgNombre = ""
        }

        return isErrorNombre
    }
}

fun isNumber(aux: String): Boolean {
    return try {
        aux.toDouble()
        false
    } catch (e: java.lang.NumberFormatException) {
        true
    }
}