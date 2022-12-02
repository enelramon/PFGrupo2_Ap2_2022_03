package edu.ucne.appliedbarbershop

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.ucne.appliedbarbershop.ui.navegacion.NavegacionViewModel
import edu.ucne.appliedbarbershop.ui.navegacion.TopBar
import edu.ucne.appliedbarbershop.ui.servicios.ServicioViewModel
import edu.ucne.appliedbarbershop.utils.Screen
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroServicioScreen(
    navController: NavController,
    navegacionViewModel: NavegacionViewModel,
    id: Int = 0,
    viewModel: ServicioViewModel = hiltViewModel(),
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    if (id > 0)
        viewModel.cargarServicio(id, navegacionViewModel)

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
            Button(
                enabled = viewModel.nombre.isNotBlank() && viewModel.enableSubmit,
                onClick = {
                    viewModel.save(localContext, navController, navegacionViewModel)
                }) {
                Icon(
                    imageVector = Icons.Filled.Done,
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
            Spacer(modifier = Modifier.height(39.dp))
            OutlinedTextField(
                label = { Text(text = "Nombre") },
                value = viewModel.nombre,
                onValueChange = { viewModel.onNombreChange(it) })
            OutlinedTextField(
                label = { Text(text = "Imagen") },
                value = viewModel.imagen,
                onValueChange = { viewModel.onImagenChange(it) })
        }
    }
}