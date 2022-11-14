package edu.ucne.appliedbarbershop.ui.servicios

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.appliedbarbershop.data.remote.api_repository.ServicioApiRepository
import edu.ucne.appliedbarbershop.data.remote.dto.ServicioDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import okio.IOException
import javax.inject.Inject

data class ServicioUiState(
    val servicios: List<ServicioDto> = emptyList()
)

@HiltViewModel
class HomeServiciosViewModel @Inject constructor(
    private val api: ServicioApiRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(ServicioUiState())
    val uiState: StateFlow<ServicioUiState> = _uiState.asStateFlow()

    init {
        recargar()
    }

    fun recargar(){
        viewModelScope.launch {
            _uiState.getAndUpdate {
                try {
                    it.copy(servicios = api.getServicios())
                }catch (ioe: IOException){
                    it.copy(servicios = emptyList())
                }
            }
        }
    }

    fun delete(servicio: ServicioDto) {
        viewModelScope.launch {
            api.deleteServicio(servicio.servicioId.toString())
        }
    }
    
}