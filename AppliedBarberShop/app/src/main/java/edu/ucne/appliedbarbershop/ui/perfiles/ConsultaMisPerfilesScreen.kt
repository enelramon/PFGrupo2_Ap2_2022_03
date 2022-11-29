package edu.ucne.appliedbarbershop

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.ucne.appliedbarbershop.ui.clientes.ClienteViewModel
import edu.ucne.appliedbarbershop.ui.navegacion.NavegacionViewModel
import edu.ucne.appliedbarbershop.ui.navegacion.TopBar
import edu.ucne.appliedbarbershop.utils.Components.CardComponent
import edu.ucne.appliedbarbershop.utils.Screen
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsultaMisClientesScreen(
    navController: NavController,
    navegacionViewModel: NavegacionViewModel,
    viewModel: ClienteViewModel = hiltViewModel(),
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
            Button(onClick = { navController.navigate(Screen.RegistroPerfilScreen.Route) }) {
                Icon(
                    imageVector = Icons.Filled.Add,
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
                items(navegacionViewModel.perfiles) {
                    CardComponent(
                        titulo = (it.nombre + " " + it.apellido) ?: "",
                        cuerpo = "Celular: " + it.celular +
                                "\nFecha de nacimiento: " + it.fechaNacimiento,
                        btn1Nombre = "Editar",
                        btn2Nombre = "Eliminar",
                        btn1OnClick = { navController.navigate(Screen.RegistroPerfilScreen.Route + "/" + it.perfilId.toString()) },
                        btn2OnClick = {},
                        clickeable = true,
                        selected = (it.perfilId == navegacionViewModel.cliente.clienteId),
                        onClick = {
                            if (it.perfilId != navegacionViewModel.cliente.clienteId)
                                viewModel.onOpenDialogCambiarPerfil(true)
                        }
                    )

                    if (viewModel.openDialogCambiarPerfil) {
                        AlertDialog(
                            onDismissRequest = {
                                viewModel.openDialogCambiarPerfil = false
                            },
                            title = {
                                Text(text = "Seguro que desea cambiar a " + it.nombre + "?")
                            },
                            confirmButton = {
                                TextButton(
                                    onClick = {
                                        viewModel.openDialogCambiarPerfil = false
                                        viewModel.cambiarDePerfil(
                                            it.perfilId,
                                            navegacionViewModel
                                        )
                                    }
                                ) {
                                    Text("Aceptar")
                                }
                            },
                            dismissButton = {
                                TextButton(
                                    onClick = {
                                        viewModel.openDialogCambiarPerfil = false
                                    }
                                ) {
                                    Text("Cancelar")
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}