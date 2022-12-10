package edu.ucne.appliedbarbershop

import androidx.compose.foundation.layout.*
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
import edu.ucne.appliedbarbershop.utils.Components.ComboFecha
import edu.ucne.appliedbarbershop.utils.Components.ComboHora
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroCita_ReservacionScreen(
    navController: NavController,
    navegacionViewModel: NavegacionViewModel,
    viewModel: CitaViewModel,
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    val localContext = LocalContext.current
    Scaffold(
        topBar = {
            TopBar(
                scope,
                navegacionViewModel.selectedItem,
                drawerState
            )
        },
        floatingActionButton = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 100.dp)
            ) {
                Button(
                    enabled = (viewModel.fch.isNotBlank() && viewModel.hra.isNotBlank()) && viewModel.enableSubmit,
                    onClick = {
                        viewModel.save(localContext, navController, navegacionViewModel)
                    }) {
                    Text(text = "Agendar")
                }
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
            Spacer(modifier = Modifier.height(39.dp))
            ComboFecha(
                label = "Fecha",
                value = viewModel.fch,
                onValueChange = { viewModel.onFchChange(it) }
            )
            ComboHora(
                label = "Hora",
                value = viewModel.hra,
                onValueChange = { viewModel.onHraChange(it) }
            )
        }
    }
}
