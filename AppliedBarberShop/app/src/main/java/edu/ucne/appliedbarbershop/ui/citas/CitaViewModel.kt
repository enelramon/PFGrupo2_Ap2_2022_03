package edu.ucne.appliedbarbershop.ui.citas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.appliedbarbershop.data.local.repository.CitaRepository
import edu.ucne.appliedbarbershop.data.local.repository.PerfilRepository
import edu.ucne.appliedbarbershop.data.remote.api_repository.CitaApiRepository
import edu.ucne.appliedbarbershop.data.remote.api_repository.PerfilApiRepository
import edu.ucne.appliedbarbershop.data.remote.dto.CitaDto
import edu.ucne.appliedbarbershop.data.remote.dto.ServicioDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import okio.IOException
import javax.inject.Inject

data class CitaUiState(
    val servicios: List<CitaDto> = emptyList()
)

@HiltViewModel
class CitaViewModel @Inject constructor(
    private val api: CitaApiRepository,
    private val citaRepository: CitaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CitaUiState())
    val uiState: StateFlow<CitaUiState> = _uiState.asStateFlow()

    init {

    }

    fun getCitasByClienteId(){
        viewModelScope.launch {
            _uiState.getAndUpdate {
                try {
                    it.copy(servicios = api.getCitasByClienteId(id = "ID del Cliente"))
                }catch (ioe: IOException){
                    it.copy(servicios = emptyList())
                }
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