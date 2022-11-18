package edu.ucne.appliedbarbershop.ui.navegacion

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.appliedbarbershop.utils.Constantes
import edu.ucne.appliedbarbershop.utils.Screen
import javax.inject.Inject

@HiltViewModel
class NavegacionViewModel @Inject constructor() : ViewModel() {

    val items = listOf(
        ItemNav(
            descripcion = "Principal",
            icono = Icons.Default.Favorite,
            screen = Screen.PrincipalScreen,
            titulo = Constantes.NombreEmpresa.value
        ),
        ItemNav(
            descripcion = "Mis Citas",
            icono = Icons.Default.Favorite,
            screen = Screen.ConsultaMisCitasScreen
        ),
        ItemNav(
            descripcion = "Mis Perfiles",
            icono = Icons.Default.Favorite,
            screen = Screen.ConsultaMisPerfilesScreen
        ),
        ItemNav(
            descripcion = "Ajustes",
            icono = Icons.Default.Favorite,
            screen = Screen.AjustesScreen
        ),
        ItemNav(
            descripcion = "Sobre Nosotros",
            icono = Icons.Default.Favorite,
            screen = Screen.IntroScreen
        ),
    )

    var selectedItem by mutableStateOf(items[0])
    var mostrarNav by mutableStateOf(false)

    val startDestination = if (true) Screen.IntroScreen.Route else Screen.PrincipalScreen.Route

    fun handleChangeSelectedItem(itemNav: ItemNav){
        selectedItem = itemNav
    }

}

class ItemNav(
    val descripcion: String,
    val icono: ImageVector,
    val screen: Screen,
    val titulo: String = descripcion
)