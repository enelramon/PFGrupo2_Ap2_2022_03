package edu.ucne.appliedbarbershop

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.ucne.appliedbarbershop.ui.citas.CitaViewModel
import edu.ucne.appliedbarbershop.ui.navegacion.NavegacionViewModel
import edu.ucne.appliedbarbershop.ui.navegacion.TopBar
import edu.ucne.appliedbarbershop.utils.Components.CardComponent
import edu.ucne.appliedbarbershop.utils.validString
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsultaCitasPendientesScreen(
    navController: NavController,
    navegacionViewModel: NavegacionViewModel,
    viewModel: CitaViewModel = hiltViewModel(),
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
                items(navegacionViewModel.citasPendientes) {
                    CardComponent(
                        titulo = (validString(it.clienteNombre) + " " + validString(it.clienteApellido)) ?: "",
                        subtitulo = it.clienteCelular ?: "",
                        cuerpo = separarFecha(it.fecha) + "\n" + "Servicio: " + it.servicioNombre,
                        btn1Nombre = "Realizar",
                        btn2Nombre = "Rechazar",
                        btnEnabled = viewModel.enableSubmit,
                        btn1OnClick = {
                            viewModel.realizar(
                                id = it.citaId,
                                localContext,
                                navController,
                                navegacionViewModel
                            )
                        },
                        btn2OnClick = {
                            viewModel.buscarCita(it.citaId, navegacionViewModel)
                            viewModel.onOpenDialogRechazarChange(true)
                        }
                    )
                }
            }
        }

        if (viewModel.openDialogRechazar) {
            AlertDialog(
                onDismissRequest = {
                    // Dismiss the dialog when the user clicks outside the dialog or on the back
                    // button. If you want to disable that functionality, simply use an empty
                    // onCloseRequest.
                    viewModel.openDialogRechazar = false
                },
                title = {
                    Text(text = "Seguro que desea rechazar?")
                },
                text = {
                    Column() {
                        Text(text = "Explique el motivo")
                        OutlinedTextField(
                            label = { Text(text = "Motivo") },
                            value = viewModel.mensaje,
                            onValueChange = { viewModel.mensaje = it })
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            viewModel.openDialogRechazar = false
                            viewModel.rechazar(
                                localContext,
                                navController,
                                navegacionViewModel
                            )
                        }
                    ) {
                        Text("Rechazar")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            viewModel.openDialogRechazar = false
                        }
                    ) {
                        Text("Cancelar")
                    }
                }
            )

        }
    }
}