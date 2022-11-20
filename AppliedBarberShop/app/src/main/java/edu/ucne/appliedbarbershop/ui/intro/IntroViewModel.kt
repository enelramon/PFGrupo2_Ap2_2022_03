package edu.ucne.appliedbarbershop.ui.intro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.appliedbarbershop.data.local.Sync
import edu.ucne.appliedbarbershop.data.local.AppDataBase
import edu.ucne.appliedbarbershop.data.remote.api_repository.CitaApiRepository
import edu.ucne.appliedbarbershop.data.remote.api_repository.ClienteApiRepository
import edu.ucne.appliedbarbershop.data.remote.api_repository.ServicioApiRepository
import edu.ucne.appliedbarbershop.data.remote.dto.CitaDto
import edu.ucne.appliedbarbershop.data.remote.dto.ClienteDto
import edu.ucne.appliedbarbershop.data.remote.dto.ServicioDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class ServicioUiState(
    val servicios: List<ServicioDto> = emptyList()
)
data class CitaUiState(
    val citas: List<CitaDto> = emptyList()
)
data class PerfilUiState(
    val clientees: List<ClienteDto> = emptyList()
)

@HiltViewModel
class IntroViewModel @Inject constructor(
    private val dataBase: AppDataBase,
    private val servicioApiRepository: ServicioApiRepository,
    private val citaApiRepository: CitaApiRepository,
    private val clienteApiRepository: ClienteApiRepository
) : ViewModel() {

    private val _uiStateServicio = MutableStateFlow(ServicioUiState())
    val uiStateServicio: StateFlow<ServicioUiState> = _uiStateServicio.asStateFlow()

    private val _uiStateCita = MutableStateFlow(CitaUiState())
    val uiStateCita: StateFlow<CitaUiState> = _uiStateCita.asStateFlow()

    private val _uiStatePerfil = MutableStateFlow(PerfilUiState())
    val uiStatePerfil: StateFlow<PerfilUiState> = _uiStatePerfil.asStateFlow()

    init {
        val sync = Sync()
        sync.sincronizarServicios(viewModelScope, _uiStateServicio, servicioApiRepository, dataBase)
        sync.sincronizarCitas(viewModelScope, _uiStateCita, citaApiRepository, dataBase)
        //sync.sincronizarClientes(viewModelScope, _uiStatePerfil, clienteApiRepository, dataBase)
    }
}