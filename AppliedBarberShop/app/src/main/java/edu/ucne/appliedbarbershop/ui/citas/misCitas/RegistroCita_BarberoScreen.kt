package edu.ucne.appliedbarbershop

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import edu.ucne.appliedbarbershop.ui.citas.CitaViewModel
import edu.ucne.appliedbarbershop.ui.navegacion.NavegacionViewModel
import edu.ucne.appliedbarbershop.ui.navegacion.TopBar
import edu.ucne.appliedbarbershop.utils.Components.CardComponent
import edu.ucne.appliedbarbershop.utils.Screen
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroCita_BarberoScreen(
    navController: NavController,
    navegacionViewModel: NavegacionViewModel,
    id: Int = 0,
    viewModel: CitaViewModel,
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    val localContext = LocalContext.current
    if (id > 0)
        viewModel.cargarCita(id, navegacionViewModel)
    Scaffold(
        topBar = {
            TopBar(
                scope,
                navegacionViewModel.selectedItem,
                drawerState
            )
        },
        floatingActionButton = {
            Button(
                enabled = viewModel.barbero.barberoId > 0,
                onClick = { navController.navigate(Screen.RegistroCita_ServicioScreen.Route) }
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    contentDescription = "Localized description"
                )
            }
        }
    ) {
        it
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 28.dp, start = 28.dp, bottom = 20.dp, top = 68.dp)
        ) {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(navegacionViewModel.barberos) {
                    CardComponent(
                        titulo = it.nombre ?: "",
                        selected = (it == viewModel.barbero),
                        clickeable = true,
                        onClick = {
                            viewModel.eligeBarbero(it)
                        }
                    )
                }
            }
        }
    }
}