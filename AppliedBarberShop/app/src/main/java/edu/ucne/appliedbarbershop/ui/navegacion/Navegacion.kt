package edu.ucne.appliedbarbershop.ui.navegacion

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.ucne.appliedbarbershop.*
import edu.ucne.appliedbarbershop.ui.citas.CitaViewModel
import edu.ucne.appliedbarbershop.ui.principal.PrincipalScreen
import edu.ucne.appliedbarbershop.ui.theme.DancingScript
import edu.ucne.appliedbarbershop.utils.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navegacion(
    navegacionViewModel: NavegacionViewModel = hiltViewModel(),
    citaViewModel: CitaViewModel = hiltViewModel()
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                scope,
                navegacionViewModel.items,
                navegacionViewModel.selectedItem,
                drawerState,
                navController,
                navegacionViewModel
            )
        },
        content = {
            Scaffold(
                topBar = {
                    if (false) TopBar(
                        scope,
                        navegacionViewModel.selectedItem,
                        drawerState
                    )
                },
                content = {
                    it
                    Column(modifier = Modifier.fillMaxSize()) {
                        NavHostComponent(
                            navController,
                            navegacionViewModel.startDestination,
                            navegacionViewModel,
                            citaViewModel,
                            drawerState,
                            scope
                        )
                    }
                }
            )
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavHostComponent(
    navController: NavHostController,
    startDestination: String,
    navegacionViewModel: NavegacionViewModel,
    citaViewModel: CitaViewModel,
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.IntroScreen.Route) {
            IntroScreen(
                navController = navController,
                navegacionViewModel = navegacionViewModel
            )
        }
        composable(route = Screen.PrincipalScreen.Route) {
            PrincipalScreen(
                navController = navController,
                navegacionViewModel = navegacionViewModel,
                drawerState = drawerState,
                scope = scope
            )
        }
        composable(route = Screen.ConsultaMisClientesScreen.Route) {
            ConsultaMisClientesScreen(
                navController = navController,
                navegacionViewModel = navegacionViewModel,
                drawerState = drawerState,
                scope = scope
            )
        }
        composable(route = Screen.RegistroPerfilScreen.Route + "/{id}") { navEntry ->
            val id = navEntry.arguments?.getString("id") ?: "0"
            RegistroClienteScreen(
                navController = navController, navegacionViewModel = navegacionViewModel,
                id = id.toInt()
            )
        }
        composable(route = Screen.RegistroPerfilScreen.Route) { navEntry ->
            RegistroClienteScreen(
                navController = navController,
                navegacionViewModel = navegacionViewModel
            )
        }
        composable(route = Screen.ConfirmaRegistroPerfilScreen.Route) {
            ConfirmaRegistroClienteScreen(
                navController = navController,
                navegacionViewModel = navegacionViewModel
            )
        }
        composable(route = Screen.ConsultaMisCitasScreen.Route) {
            ConsultaMisCitasScreen(
                navController = navController,
                navegacionViewModel = navegacionViewModel,
                drawerState = drawerState,
                scope = scope
            )
        }
        composable(route = Screen.RegistroCita_BarberoScreen.Route + "/{id}") { navEntry ->
            val id = navEntry.arguments?.getString("id") ?: "0"
            RegistroCita_BarberoScreen(
                navController = navController, navegacionViewModel = navegacionViewModel,
                id = id.toInt(),
                viewModel = citaViewModel,
                drawerState = drawerState,
                scope = scope
            )
        }
        composable(route = Screen.RegistroCita_BarberoScreen.Route) { navEntry ->
            RegistroCita_BarberoScreen(
                navController = navController, navegacionViewModel = navegacionViewModel,
                viewModel = citaViewModel,
                drawerState = drawerState,
                scope = scope
            )
        }
        composable(route = Screen.RegistroCita_ServicioScreen.Route + "/{id}") { navEntry ->
            val id = navEntry.arguments?.getString("id") ?: "0"
            RegistroCita_ServicioScreen(
                navController = navController, navegacionViewModel = navegacionViewModel,
                viewModel = citaViewModel,
                drawerState = drawerState,
                scope = scope
            )
        }
        composable(route = Screen.RegistroCita_ServicioScreen.Route) { navEntry ->
            RegistroCita_ServicioScreen(
                navController = navController, navegacionViewModel = navegacionViewModel,
                viewModel = citaViewModel,
                drawerState = drawerState,
                scope = scope
            )
        }
        composable(route = Screen.RegistroCita_ReservacionScreen.Route + "/{id}") { navEntry ->
            val id = navEntry.arguments?.getString("id") ?: "0"
            RegistroCita_ReservacionScreen(
                navController = navController, navegacionViewModel = navegacionViewModel,
                viewModel = citaViewModel,
                drawerState = drawerState,
                scope = scope
            )
        }
        composable(route = Screen.RegistroCita_ReservacionScreen.Route) { navEntry ->
            RegistroCita_ReservacionScreen(
                navController = navController, navegacionViewModel = navegacionViewModel,
                viewModel = citaViewModel,
                drawerState = drawerState,
                scope = scope
            )
        }
        composable(route = Screen.ConfirmaRegistroCitaScreen.Route) {
            ConfirmaRegistroCitaScreen(
                navController = navController,
                navegacionViewModel = navegacionViewModel,
                viewModel = citaViewModel
            )
        }
        composable(route = Screen.ConsultaCitasPendientesScreen.Route) {
            ConsultaCitasPendientesScreen(
                navController = navController,
                navegacionViewModel = navegacionViewModel,
                drawerState = drawerState,
                scope = scope
            )
        }
        composable(route = Screen.ConsultaHistorialCitasScreen.Route) {
            ConsultaHistorialCitasScreen(
                navController = navController,
                navegacionViewModel = navegacionViewModel,
                drawerState = drawerState,
                scope = scope
            )
        }
        composable(route = Screen.ConsultaServiciosScreen.Route) {
            ConsultaServiciosScreen(
                navController = navController,
                navegacionViewModel = navegacionViewModel,
                drawerState = drawerState,
                scope = scope
            )
        }
        composable(route = Screen.RegistroServicioScreen.Route + "/{id}") { navEntry ->
            val id = navEntry.arguments?.getString("id") ?: "0"
            RegistroServicioScreen(
                navController = navController, navegacionViewModel = navegacionViewModel,
                id = id.toInt(),
                drawerState = drawerState,
                scope = scope
            )
        }
        composable(route = Screen.RegistroServicioScreen.Route) { navEntry ->
            RegistroServicioScreen(
                navController = navController,
                navegacionViewModel = navegacionViewModel,
                drawerState = drawerState,
                scope = scope
            )
        }
        composable(route = Screen.AjustesScreen.Route) {
            AjustesScreen(
                navController = navController,
                navegacionViewModel = navegacionViewModel,
                drawerState = drawerState,
                scope = scope
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DrawerContent(
    scope: CoroutineScope,
    items: List<ItemNav>,
    selectedItem: ItemNav,
    drawerState: DrawerState,
    navController: NavHostController,
    navegacionViewModel: NavegacionViewModel
) {
    val activity = (LocalContext.current as? Activity)

    ModalDrawerSheet {
        Spacer(Modifier.height(50.dp))
        Text(
            text = navegacionViewModel.cliente.nombre + " " + navegacionViewModel.cliente.apellido,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(50.dp))
        items.forEach { item ->
            NavigationDrawerItem(
                icon = { Icon(item.icono, contentDescription = null) },
                label = { Text(item.descripcion) },
                selected = item == selectedItem,
                onClick = {
                    scope.launch { drawerState.close() }
                    navController.navigate(item.screen.Route)
                    navegacionViewModel.onChangeSelectedItem(item)
                },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
        NavigationDrawerItem(
            icon = { Icon(Icons.Default.Logout, contentDescription = null) },
            label = { Text("Salir") },
            selected = false,
            onClick = {
                scope.launch { drawerState.close() }
                activity?.finish()
            },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(scope: CoroutineScope, selectedItem: ItemNav, drawerState: DrawerState) {
    CenterAlignedTopAppBar(
        title = {
            if (selectedItem.descripcion == "Principal")
                Text(
                    selectedItem.titulo,
                    fontFamily = DancingScript,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            else
                Text(
                    selectedItem.titulo,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
        },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    drawerState.open()
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Localized description"
                )
            }
        }
    )
}