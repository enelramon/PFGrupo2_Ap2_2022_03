package edu.ucne.appliedbarbershop.ui.navegacion

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.appliedbarbershop.utils.Constantes
import edu.ucne.appliedbarbershop.utils.Screen
import javax.inject.Inject

@HiltViewModel
class NavegacionViewModel @Inject constructor() : ViewModel() {

    val items = mutableListOf(
        ItemNav(
            descripcion = "Principal",
            icono = Icons.Default.Home,
            screen = Screen.PrincipalScreen,
            titulo = Constantes.NombreEmpresa.value
        )
    )

    init {
        // TODO agregar la condicion de si es barbero
        if (true) {
            items.addAll(
                mutableListOf(
                    ItemNav(
                        descripcion = "Citas Pendientes",
                        icono = Icons.Default.Schedule,
                        screen = Screen.ConsultaCitasPendientesScreen
                    ),
                    ItemNav(
                        descripcion = "Historial de Citas",
                        icono = Icons.Default.EventAvailable,
                        screen = Screen.ConsultaHistorialCitasScreen
                    ),
                    ItemNav(
                        descripcion = "Servicios",
                        icono = Icons.Default.Favorite,
                        screen = Screen.ConsultaServiciosScreen
                    )
                )
            )
        }
        items.addAll(
            mutableListOf(
                ItemNav(
                    descripcion = "Mis Citas",
                    icono = Icons.Default.EditCalendar,
                    screen = Screen.ConsultaMisCitasScreen
                ),
                ItemNav(
                    descripcion = "Mis Perfiles",
                    icono = Icons.Default.Person,
                    screen = Screen.ConsultaMisClientesScreen
                ),
                ItemNav(
                    descripcion = "Ajustes",
                    icono = Icons.Default.Settings,
                    screen = Screen.AjustesScreen
                ),
                ItemNav(
                    descripcion = "Sobre Nosotros",
                    icono = Icons.Default.HelpOutline,
                    screen = Screen.IntroScreen
                )
            )
        )
    }

    var selectedItem by mutableStateOf(items[0])
    var mostrarNav by mutableStateOf(false)

    val startDestination = if (false) Screen.IntroScreen.Route else Screen.PrincipalScreen.Route

    fun onChangeSelectedItem(itemNav: ItemNav) {
        selectedItem = itemNav
    }

    fun onChangeMostrarNav(t: Boolean) {
        if (!t) selectedItem = items.get(0)
        mostrarNav = t
    }

}

class ItemNav(
    val descripcion: String,
    val icono: ImageVector,
    val screen: Screen,
    val titulo: String = descripcion
)