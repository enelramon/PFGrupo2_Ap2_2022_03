package edu.ucne.appliedbarbershop.ui.intro

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.appliedbarbershop.data.local.models.Entorno
import edu.ucne.appliedbarbershop.data.local.repository.EntornoRepository
import edu.ucne.appliedbarbershop.ui.navegacion.NavegacionViewModel
import edu.ucne.appliedbarbershop.utils.Screen
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AjustesViewModel @Inject constructor(
    private val entornoRepository: EntornoRepository
) : ViewModel() {
    var codigoBarbero by mutableStateOf("")

    fun onCodigoBarberoChange(t: String) {
        codigoBarbero = t
    }

    fun autenticarBarbero(
        localContext: Context,
        navegacionViewModel: NavegacionViewModel,
        navController: NavController
    ) {
        viewModelScope.launch {
            entornoRepository.getEntorno().collect {
                if (it != null) {
                    if (it.codigoBarberia == codigoBarbero) {
                        entornoRepository.insert(
                            Entorno(
                                entornoId = it.entornoId,
                                codigoBarberia = it.codigoBarberia,
                                clienteIdSeleccionado = it.clienteIdSeleccionado,
                                isBarberoAutenticado = true
                            )
                        )
                        codigoBarbero = ""
                        navegacionViewModel.isBarberoAutenticado = true
                        navegacionViewModel.sincronizarEntorno()
                        navController.navigate(Screen.PrincipalScreen.Route)
                    } else {
                        Toast.makeText(localContext, "Código inválido!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}