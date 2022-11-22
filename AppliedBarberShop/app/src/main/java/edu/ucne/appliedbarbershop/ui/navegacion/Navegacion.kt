package edu.ucne.appliedbarbershop.ui.navegacion

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
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
                    if (navegacionViewModel.mostrarNav) TopBar(
                        scope,
                        navegacionViewModel.selectedItem,
                        drawerState
                    )
                },
                content = {
                    it
                    val mdf: Modifier
                    if (navegacionViewModel.mostrarNav)
                        mdf = Modifier.fillMaxSize().padding(top = 60.dp)
                    else
                        mdf = Modifier.fillMaxSize()

                    Column(modifier = mdf) {
                        NavHostComponent(
                            navController,
                            navegacionViewModel.startDestination,
                            navegacionViewModel,
                            citaViewModel
                        )
                    }
                }
            )
        }
    )
}

@Composable
fun NavHostComponent(
    navController: NavHostController,
    startDestination: String,
    navegacionViewModel: NavegacionViewModel,
    citaViewModel: CitaViewModel
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.IntroScreen.Route) {
            navegacionViewModel.onChangeMostrarNav(false)
            IntroScreen(navController = navController)
        }
        composable(route = Screen.PrincipalScreen.Route) {
            navegacionViewModel.onChangeMostrarNav(true)
            PrincipalScreen(navController = navController)
        }
        composable(route = Screen.ConsultaMisClientesScreen.Route) {
            navegacionViewModel.onChangeMostrarNav(true)
            ConsultaMisClientesScreen(navController = navController)
        }
        composable(route = Screen.RegistroPerfilScreen.Route + "/{id}") { navEntry ->
            navegacionViewModel.onChangeMostrarNav(false)
            val id = navEntry.arguments?.getString("id") ?: "0"
            RegistroPerfilScreen(
                navController = navController,
                id = id.toInt()
            )
        }
        composable(route = Screen.RegistroPerfilScreen.Route) { navEntry ->
            navegacionViewModel.onChangeMostrarNav(false)
            RegistroPerfilScreen(navController = navController)
        }
        composable(route = Screen.ConfirmaRegistroPerfilScreen.Route) {
            navegacionViewModel.onChangeMostrarNav(false)
            ConfirmaRegistroPerfilScreen(navController = navController)
        }
        composable(route = Screen.ConsultaMisCitasScreen.Route) {
            navegacionViewModel.onChangeMostrarNav(true)
            ConsultaMisCitasScreen(navController = navController)
        }
        composable(route = Screen.RegistroCita_BarberoScreen.Route + "/{id}") { navEntry ->
            navegacionViewModel.onChangeMostrarNav(true)
            val id = navEntry.arguments?.getString("id") ?: "0"
            RegistroCita_BarberoScreen(
                navController = navController,
                id = id.toInt(),
                viewModel = citaViewModel
            )
        }
        composable(route = Screen.RegistroCita_BarberoScreen.Route) { navEntry ->
            navegacionViewModel.onChangeMostrarNav(true)
            RegistroCita_BarberoScreen(
                navController = navController,
                viewModel = citaViewModel
            )
        }
        composable(route = Screen.RegistroCita_ServicioScreen.Route + "/{id}") { navEntry ->
            navegacionViewModel.onChangeMostrarNav(true)
            val id = navEntry.arguments?.getString("id") ?: "0"
            RegistroCita_ServicioScreen(
                navController = navController,
                viewModel = citaViewModel
            )
        }
        composable(route = Screen.RegistroCita_ServicioScreen.Route) { navEntry ->
            navegacionViewModel.onChangeMostrarNav(true)
            RegistroCita_ServicioScreen(
                navController = navController,
                viewModel = citaViewModel
            )
        }
        composable(route = Screen.RegistroCita_ReservacionScreen.Route + "/{id}") { navEntry ->
            navegacionViewModel.onChangeMostrarNav(true)
            val id = navEntry.arguments?.getString("id") ?: "0"
            RegistroCita_ReservacionScreen(
                navController = navController,
                viewModel = citaViewModel
            )
        }
        composable(route = Screen.RegistroCita_ReservacionScreen.Route) { navEntry ->
            navegacionViewModel.onChangeMostrarNav(true)
            RegistroCita_ReservacionScreen(
                navController = navController,
                viewModel = citaViewModel
            )
        }
        composable(route = Screen.ConfirmaRegistroCitaScreen.Route) {
            navegacionViewModel.onChangeMostrarNav(false)
            ConfirmaRegistroCitaScreen(navController = navController)
        }
        composable(route = Screen.ConsultaCitasPendientesScreen.Route) {
            navegacionViewModel.onChangeMostrarNav(true)
            ConsultaCitasPendientesScreen(navController = navController)
        }
        composable(route = Screen.ConsultaHistorialCitasScreen.Route) {
            navegacionViewModel.onChangeMostrarNav(true)
            ConsultaHistorialCitasScreen(navController = navController)
        }
        composable(route = Screen.ConsultaServiciosScreen.Route) {
            navegacionViewModel.onChangeMostrarNav(true)
            ConsultaServiciosScreen(navController = navController)
        }
        composable(route = Screen.RegistroServicioScreen.Route + "/{id}") { navEntry ->
            navegacionViewModel.onChangeMostrarNav(true)
            val id = navEntry.arguments?.getString("id") ?: "0"
            RegistroServicioScreen(
                navController = navController,
                id = id.toInt()
            )
        }
        composable(route = Screen.RegistroServicioScreen.Route) { navEntry ->
            navegacionViewModel.onChangeMostrarNav(true)
            RegistroServicioScreen(navController = navController)
        }
        composable(route = Screen.AjustesScreen.Route) {
            navegacionViewModel.onChangeMostrarNav(true)
            AjustesScreen(navController = navController)
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
            text = "Albert Mendoza",
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