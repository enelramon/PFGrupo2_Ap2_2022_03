package edu.ucne.appliedbarbershop.ui.intro

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AjustesViewModel @Inject constructor(
    //private val ajustesRepository: AjustesRepository
) : ViewModel() {
    var codigoBarbero by mutableStateOf("")

    fun onCodigoBarberoChange(t: String){
        codigoBarbero = t
    }
}