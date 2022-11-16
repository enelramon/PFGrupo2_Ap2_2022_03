package edu.ucne.appliedbarbershop.ui.perfiles

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.appliedbarbershop.data.local.repository.PerfilRepository
import edu.ucne.appliedbarbershop.data.local.repository.ServicioRepository
import edu.ucne.appliedbarbershop.data.remote.api_repository.PerfilApiRepository
import edu.ucne.appliedbarbershop.data.remote.api_repository.ServicioApiRepository
import edu.ucne.appliedbarbershop.data.remote.dto.PerfilDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import okio.IOException
import javax.inject.Inject

data class PerfilUiState(
    val perfiles: List<PerfilDto> = emptyList()
)

@HiltViewModel
class PerfilViewModel @Inject constructor(
    private val api: PerfilApiRepository,
    private val perfilRepository: PerfilRepository
) : ViewModel() {
    var perfilId by mutableStateOf("")
    var nombre by mutableStateOf("")
    var apellido by mutableStateOf("")
    var celular by mutableStateOf("")
    var fechaNacimiento by mutableStateOf("")
    var imagen by mutableStateOf("")
    var status by mutableStateOf("")

    fun onPerfilIdChange(t: String){ perfilId = t}
    fun onNombreChange(t: String){ nombre = t}
    fun onApellidoChange(t: String){ apellido = t}
    fun onCelularChange(t: String){ celular = t}
    fun onFechaNacimientoChange(t: String){ fechaNacimiento = t}
    fun onImagenChange(t: String){ imagen = t}
    fun onStatusChange(t: String){ status = t}
}