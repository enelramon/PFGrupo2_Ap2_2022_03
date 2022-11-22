package edu.ucne.appliedbarbershop.ui.clientees

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.appliedbarbershop.data.local.repository.ClienteRepository
import edu.ucne.appliedbarbershop.data.remote.api_repository.ClienteApiRepository
import edu.ucne.appliedbarbershop.data.remote.dto.ClienteDto
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PerfilUiState(
    val clientees: List<ClienteDto> = emptyList()
)

@HiltViewModel
class PerfilViewModel @Inject constructor(
    private val api: ClienteApiRepository,
    private val clienteRepository: ClienteRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PerfilUiState())
    val uiState: StateFlow<PerfilUiState> = _uiState.asStateFlow()

    var clienteId by mutableStateOf("0")
    var nombre by mutableStateOf("")
    var apellido by mutableStateOf("")
    var celular by mutableStateOf("")
    var fechaNacimiento by mutableStateOf("")
    var imagen by mutableStateOf("")
    var status by mutableStateOf("1")

    fun onPerfilIdChange(t: String) {
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

    fun save(): MutableLiveData<Boolean> {
        enableSubmit = false
        val result = MutableLiveData<Boolean>()
        viewModelScope.launch {
            if (api.insertPerfil(
                    ClienteDto(
                        clienteId = clienteId.toInt(),
                        nombre = nombre,
                        apellido = apellido,
                        celular = celular,
                        fechaNacimiento = fechaNacimiento,
                        imagen = imagen,
                        status = status.toInt()
                    )
                )
            ) {
//                if (clienteRepository.insert(
//                        Perfil(
//                            clienteId = clienteId.toInt(),
//                            nombre = nombre,
//                            apellido = apellido,
//                            celular = celular,
//                            fechaNacimiento = fechaNacimiento,
//                            imagen = imagen,
//                            status = status.toInt()
//                        )
//                    )
//                ) {
//                    result.postValue(true)
//                } else {
//                    result.postValue(false)
//                }

                result.postValue(true)

            } else {
                result.postValue(false)
            }
            enableSubmit = true
        }
        return result
    }
    init {

    }

    fun getPerfilByClienteStatus(){
        viewModelScope.launch{
            _uiState.getAndUpdate {
                try {
                    it.copy(perfiles = api.getAllPerfilStatus())
                }catch (ioe: IOException){
                    it.copy(perfiles = emptyList())
                }
            }
        }
    }

    fun update(id: String, perfil: PerfilDto){
        viewModelScope.launch {
            api.updatePerfil(id, perfil)
        }
    }

    fun searchById(id: String){
        viewModelScope.launch{
            api.getPerfil(id)
        }
    }

    fun save(perfil: PerfilDto){
        viewModelScope.launch {
            api.insertPerfil(perfil)
        }
    }

    fun delete(perfil: PerfilDto){
        viewModelScope.launch {
            api.deletePerfil(perfil.perfilId.toString())
        }
    }
}