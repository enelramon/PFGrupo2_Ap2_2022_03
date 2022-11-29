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
import edu.ucne.appliedbarbershop.data.local.models.Entorno
import edu.ucne.appliedbarbershop.data.local.models.Perfil
import edu.ucne.appliedbarbershop.data.local.repository.ClienteRepository
import edu.ucne.appliedbarbershop.data.local.repository.EntornoRepository
import edu.ucne.appliedbarbershop.data.local.repository.PerfilRepository
import edu.ucne.appliedbarbershop.data.remote.api_repository.ClienteApiRepository
import edu.ucne.appliedbarbershop.data.remote.dto.ClienteDto
import edu.ucne.appliedbarbershop.ui.navegacion.NavegacionViewModel
import edu.ucne.appliedbarbershop.utils.Screen
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class ClienteViewModel @Inject constructor(
    private val api: ClienteApiRepository,
    private val clienteRepository: ClienteRepository,
    private val entornoRepository: EntornoRepository,
    private val perfilRepository: PerfilRepository
) : ViewModel() {

    var clienteId by mutableStateOf("0")
    var nombre by mutableStateOf("")
    var apellido by mutableStateOf("")
    var celular by mutableStateOf("")
    var fechaNacimiento by mutableStateOf("")
    var imagen by mutableStateOf("")
    var status by mutableStateOf("1")

    var openDialogCambiarPerfil by mutableStateOf(false)

    fun onOpenDialogCambiarPerfil(t: Boolean) {
        openDialogCambiarPerfil = t
    }

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
                val clienteDb = Cliente(
                    clienteId = intentoGuardar.clienteId,
                    nombre = intentoGuardar.nombre,
                    apellido = intentoGuardar.apellido,
                    celular = intentoGuardar.celular,
                    fechaNacimiento = intentoGuardar.fechaNacimiento,
                    imagen = intentoGuardar.imagen,
                    status = intentoGuardar.status.toInt()
                )
                clienteRepository.insert(clienteDb)
                perfilRepository.insert(
                    Perfil(
                        perfilId = clienteDb.clienteId
                    )
                )
                navegacionViewModel.cliente = clienteDb

                cambiarDePerfil(clienteDb.clienteId, navegacionViewModel)

                if (true) {
                    navController.navigate(Screen.ConfirmaRegistroPerfilScreen.Route)
                } else {
                    Toast.makeText(localContext, "No se pudo guardar!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(localContext, "No se pudo guardar!", Toast.LENGTH_SHORT).show()
            }
            enableSubmit = true
        }
    }

    fun cambiarDePerfil(id: Int, navegacionViewModel: NavegacionViewModel) {
        viewModelScope.launch {
            entornoRepository.getEntorno().collect {
                if (it != null) {
                    entornoRepository.insert(
                        Entorno(
                            entornoId = it.entornoId,
                            codigoBarberia = it.codigoBarberia,
                            clienteIdSeleccionado = id,
                            isBarberoAutenticado = false
                        )
                    )
                }
                navegacionViewModel.isBarberoAutenticado = false
                navegacionViewModel.sincronizarEntorno()
            }
        }
    }
}