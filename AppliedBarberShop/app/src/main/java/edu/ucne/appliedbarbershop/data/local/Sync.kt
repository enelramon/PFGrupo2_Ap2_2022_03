package edu.ucne.appliedbarbershop.data.local

import edu.ucne.appliedbarbershop.data.local.models.Cita
import edu.ucne.appliedbarbershop.data.local.models.Perfil
import edu.ucne.appliedbarbershop.data.local.models.Servicio
import edu.ucne.appliedbarbershop.data.remote.api_repository.CitaApiRepository
import edu.ucne.appliedbarbershop.data.remote.api_repository.PerfilApiRepository
import edu.ucne.appliedbarbershop.data.remote.api_repository.ServicioApiRepository
import edu.ucne.appliedbarbershop.ui.intro.CitaUiState
import edu.ucne.appliedbarbershop.ui.intro.PerfilUiState
import edu.ucne.appliedbarbershop.ui.intro.ServicioUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import java.io.IOException

public class Sync {

    public fun sincronizarServicios(
        viewModelScope: CoroutineScope,
        _uiState: MutableStateFlow<ServicioUiState>,
        apiRepository: ServicioApiRepository,
        dataBase: AppDataBase
    ) {
        viewModelScope.launch {
            _uiState.getAndUpdate {
                try {
                    val servicios = apiRepository.getServicios()
                    servicios.forEach {
                        dataBase.servicioDao.insert(
                            Servicio(
                                it.servicioId,
                                it.nombre,
                                it.imagen,
                                it.usuarioCreacionId,
                                it.usuarioModificacionId,
                                it.status
                            )
                        )
                    }
                    it.copy(servicios = servicios)
                } catch (ioe: IOException) {
                    it.copy(servicios = emptyList())
                }
            }
        }
    }

    fun sincronizarCitas(
        viewModelScope: CoroutineScope,
        _uiState: MutableStateFlow<CitaUiState>,
        apiRepository: CitaApiRepository,
        dataBase: AppDataBase
    ) {
        viewModelScope.launch {
            _uiState.getAndUpdate {
                try {
                    // Reemplazar por método getCitasPorPerfilId
                    val citas = apiRepository.getCitas()
                    citas.forEach {
                        dataBase.citaDao.insert(
                            Cita(
                                it.citaId,
                                it.servicioId,
                                it.barberoId,
                                it.perfilId,
                                it.fecha,
                                it.mensaje,
                                it.usuarioCreacionId,
                                it.usuarioModificacionId,
                                it.status
                            )
                        )
                    }
                    it.copy(citas = citas)
                } catch (ioe: IOException) {
                    it.copy(citas = emptyList())
                }
            }
        }
    }

    fun sincronizarPerfiles(
        viewModelScope: CoroutineScope,
        _uiState: MutableStateFlow<PerfilUiState>,
        apiRepository: PerfilApiRepository,
        dataBase: AppDataBase
    ) {
        viewModelScope.launch {
            _uiState.getAndUpdate {
                try {
                    // Reemplazar por método getPerfilesPorPerfilId
                    val perfiles = apiRepository.getPerfiles()
                    perfiles.forEach {
                        dataBase.perfilDao.insert(
                            Perfil(
                                it.perfilId,
                                it.nombre,
                                it.apellido,
                                it.celular,
                                it.fechaNacimiento,
                                it.imagen,
                                it.status
                            )
                        )
                    }
                    it.copy(perfiles = perfiles)
                } catch (ioe: IOException) {
                    it.copy(perfiles = emptyList())
                }
            }
        }
    }
}