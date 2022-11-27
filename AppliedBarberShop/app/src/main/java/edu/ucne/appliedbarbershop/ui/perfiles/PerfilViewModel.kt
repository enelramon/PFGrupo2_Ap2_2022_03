package edu.ucne.appliedbarbershop.ui.clientes

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.appliedbarbershop.data.local.models.Cliente
import edu.ucne.appliedbarbershop.data.local.repository.ClienteRepository
import edu.ucne.appliedbarbershop.data.remote.api_repository.ClienteApiRepository
import edu.ucne.appliedbarbershop.data.remote.dto.ClienteDto
import edu.ucne.appliedbarbershop.ui.navegacion.NavegacionViewModel
import edu.ucne.appliedbarbershop.utils.Screen
import edu.ucne.appliedbarbershop.utils.Validator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import java.io.IOException
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class ClienteViewModel @Inject constructor(
    private val api: ClienteApiRepository,
    private val clienteRepository: ClienteRepository
) : ViewModel() {

    var clienteId by mutableStateOf("0")
    var nombre by mutableStateOf("")
    var apellido by mutableStateOf("")
    var celular by mutableStateOf("")
    var fechaNacimiento by mutableStateOf("")
    var imagen by mutableStateOf("")
    var status by mutableStateOf("1")

    fun onClienteIdChange(t: String) {
        clienteId = t
    }

    fun onNombreChange(t: String) {
        nombre = t
    }

    fun onApellidoChange(t: String) {
        apellido = t
    }

    fun onCelularChange(t: String) {
        celular = t
    }

    fun onFechaNacimientoChange(t: String) {
        fechaNacimiento = t
    }

    fun onImagenChange(t: String) {
        imagen = t
    }

    fun onStatusChange(t: String) {
        status = t
    }

    var enableSubmit by mutableStateOf(true)

    fun save(
        localContext: Context,
        navController: NavController,
        navegacionViewModel: NavegacionViewModel
    ) {
        enableSubmit = false
        viewModelScope.launch {
            val intentoGuardar = api.insertCliente(
                ClienteDto(
                    clienteId = clienteId.toInt(),
                    nombre = nombre,
                    apellido = apellido,
                    celular = celular,
                    fechaNacimiento = fechaNacimiento,
                    imagen = imagen,
                    fechaCreacion = LocalDateTime.now().toString(),
                    fechaModificacion = LocalDateTime.now().toString(),
                    status = status.toInt()
                )
            )
            if (
                intentoGuardar.clienteId > 0
            ) {
                var clienteDb = Cliente(
                    clienteId = intentoGuardar.clienteId,
                    nombre = intentoGuardar.nombre,
                    apellido = intentoGuardar.apellido,
                    celular = intentoGuardar.celular,
                    fechaNacimiento = intentoGuardar.fechaNacimiento,
                    imagen = intentoGuardar.imagen,
                    status = intentoGuardar.status.toInt()
                )
                clienteRepository.insert(clienteDb)
                if (true) {
                    navegacionViewModel.cliente = clienteDb
                    navController.navigate(Screen.ConfirmaRegistroPerfilScreen.Route)
                } else {
                    Toast.makeText(localContext, "No se pudo guardar!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(localContext, validar(), Toast.LENGTH_SHORT).show()
            }
            enableSubmit = true
        }
    }

    private fun validar(): String{

        var msg = ""

        if (nombre.isEmpty()){
            msg = "*Se debe llenar el campo Nombre*"
        } else if(!(nombre.any{it.isLetter()})){
            msg = "*Nombre no valido*"
        } else if(apellido.isEmpty()){
            msg = "*Se debe llenar el campo Apellido*"
        } else if(!(apellido.any{it.isLetter()})){
            msg = "*Apellido no valido*"
        } else if(celular.isEmpty()){
            msg = "*Se debe llenar el campo Celular*"
        } else if (celular.length < 10 && celular.isNotEmpty()) {
            msg = "*El campo *Celular* debe tener al menos 10 Digitos*"
        } else if (!isNumeric(celular)) {
            msg = "*El campo celular tiene simbolos no permitidos*"
        } else{
            msg = "Revise su coneccion a internet"
        }

        return  msg
    }

    private fun isNumeric(aux: String): Boolean{
        return try{
            aux.toDouble()
            true
        }catch(e: NumberFormatException){
            false
        }
    }

}