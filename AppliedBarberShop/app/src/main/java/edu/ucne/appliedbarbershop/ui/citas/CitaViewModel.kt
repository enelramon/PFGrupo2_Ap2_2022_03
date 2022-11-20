package edu.ucne.appliedbarbershop.ui.citas

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.appliedbarbershop.data.local.repository.CitaRepository
import edu.ucne.appliedbarbershop.data.remote.api_repository.CitaApiRepository
import edu.ucne.appliedbarbershop.data.remote.dto.CitaDto
import javax.inject.Inject

data class CitaUiState(
    val servicios: List<CitaDto> = emptyList()
)

@HiltViewModel
class CitaViewModel @Inject constructor(
    private val api: CitaApiRepository,
    private val citaRepository: CitaRepository
) : ViewModel() {

}