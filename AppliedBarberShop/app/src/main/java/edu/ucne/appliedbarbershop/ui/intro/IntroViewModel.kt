package edu.ucne.appliedbarbershop.ui.intro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.appliedbarbershop.data.local.AppDataBase
import edu.ucne.appliedbarbershop.data.local.repository.ClienteRepository
import edu.ucne.appliedbarbershop.data.remote.api_repository.CitaApiRepository
import edu.ucne.appliedbarbershop.data.remote.api_repository.ClienteApiRepository
import edu.ucne.appliedbarbershop.data.remote.api_repository.ServicioApiRepository
import edu.ucne.appliedbarbershop.data.remote.dto.CitaDto
import edu.ucne.appliedbarbershop.data.remote.dto.ServicioDto
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor(
    private val dataBase: AppDataBase,
    private val servicioApiRepository: ServicioApiRepository,
    private val citaApiRepository: CitaApiRepository,
    private val clienteApiRepository: ClienteApiRepository,
    private val clienteRepository: ClienteRepository
) : ViewModel() {

}