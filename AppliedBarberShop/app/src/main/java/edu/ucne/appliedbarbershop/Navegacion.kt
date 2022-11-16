package edu.ucne.appliedbarbershop

import android.app.Activity
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.ucne.appliedbarbershop.ui.citas.CitaViewModel
import edu.ucne.appliedbarbershop.ui.principal.PrincipalScreen
import edu.ucne.appliedbarbershop.ui.theme.DancingScript
import edu.ucne.appliedbarbershop.utils.Constantes
import edu.ucne.appliedbarbershop.utils.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ItemNav(
    val Descripcion: String,
    val Icono: ImageVector,
    val Titulo: String = Descripcion
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navegacion(citaViewModel: CitaViewModel = hiltViewModel()) {

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val items = listOf(
        ItemNav("Principal", Icons.Default.Favorite, Constantes.NombreEmpresa.value),
        ItemNav("Mis Citas", Icons.Default.Favorite),
        ItemNav("Mis Perfiles", Icons.Default.Favorite),
        ItemNav("Ajustes", Icons.Default.Favorite),
        ItemNav("Sobre Nosotros", Icons.Default.Favorite),
    )

    val selectedItem = remember { mutableStateOf(items[0]) }

    val navController = rememberNavController()
    val startDestination = if (true) Screen.IntroScreen.Route else Screen.PrincipalScreen.Route

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { DrawerContent(scope, items, selectedItem, drawerState) },
        content = {
            Scaffold(
                topBar = { if (false) TopBar(scope, selectedItem, drawerState) },
                content = {
                    it
                    NavHost(
                        navController = navController,
                        startDestination = startDestination
                    ) {
                        composable(route = Screen.IntroScreen.Route) {
                            IntroScreen(navController = navController)
                        }
                        composable(route = Screen.PrincipalScreen.Route) {
                            PrincipalScreen(navController = navController)
                        }
                        composable(route = Screen.ConsultaMisPerfilesScreen.Route) {
                            ConsultaMisPerfilesScreen(navController = navController)
                        }
                        composable(route = Screen.RegistroPerfilScreen.Route + "/{id}") { navEntry ->
                            val id = navEntry.arguments?.getString("id") ?: "0"
                            RegistroPerfilScreen(
                                navController = navController,
                                id = id.toInt()
                            )
                        }
                        composable(route = Screen.RegistroPerfilScreen.Route) { navEntry ->
                            RegistroPerfilScreen(navController = navController)
                        }
                        composable(route = Screen.ConfirmaRegistroPerfilScreen.Route) {
                            ConfirmaRegistroPerfilScreen(navController = navController)
                        }
                        composable(route = Screen.ConsultaMisCitasScreen.Route) {
                            ConsultaMisCitasScreen(navController = navController)
                        }
                        composable(route = Screen.RegistroCita_BarberoScreen.Route + "/{id}") { navEntry ->
                            val id = navEntry.arguments?.getString("id") ?: "0"
                            RegistroCita_BarberoScreen(
                                navController = navController,
                                id = id.toInt(),
                                viewModel = citaViewModel
                            )
                        }
                        composable(route = Screen.RegistroCita_BarberoScreen.Route) { navEntry ->
                            RegistroCita_BarberoScreen(navController = navController, viewModel = citaViewModel)
                        }
                        composable(route = Screen.RegistroCita_ServicioScreen.Route + "/{id}") { navEntry ->
                            val id = navEntry.arguments?.getString("id") ?: "0"
                            RegistroCita_ServicioScreen(
                                navController = navController,
                                viewModel = citaViewModel
                            )
                        }
                        composable(route = Screen.RegistroCita_ServicioScreen.Route) { navEntry ->
                            RegistroCita_ServicioScreen(navController = navController, viewModel = citaViewModel)
                        }
                        composable(route = Screen.RegistroCita_ReservacionScreen.Route + "/{id}") { navEntry ->
                            val id = navEntry.arguments?.getString("id") ?: "0"
                            RegistroCita_ReservacionScreen(
                                navController = navController,
                                viewModel = citaViewModel
                            )
                        }
                        composable(route = Screen.RegistroCita_ReservacionScreen.Route) { navEntry ->
                            RegistroCita_ReservacionScreen(navController = navController, viewModel = citaViewModel)
                        }
                        composable(route = Screen.ConfirmaRegistroCitaScreen.Route) {
                            ConfirmaRegistroCitaScreen(navController = navController)
                        }
                        composable(route = Screen.ConsultaCitasPendientesScreen.Route) {
                            ConsultaCitasPendientesScreen(navController = navController)
                        }
                        composable(route = Screen.ConsultaHistorialCitasScreen.Route) {
                            ConsultaHistorialCitasScreen(navController = navController)
                        }
                        composable(route = Screen.ConsultaServiciosScreen.Route) {
                            ConsultaServiciosScreen(navController = navController)
                        }
                        composable(route = Screen.RegistroServicioScreen.Route + "/{id}") { navEntry ->
                            val id = navEntry.arguments?.getString("id") ?: "0"
                            RegistroServicioScreen(
                                navController = navController,
                                id = id.toInt()
                            )
                        }
                        composable(route = Screen.RegistroServicioScreen.Route) { navEntry ->
                            RegistroServicioScreen(navController = navController)
                        }
                        composable(route = Screen.AjustesScreen.Route) {
                            AjustesScreen(navController = navController)
                        }
                    }
                }
            )
        }
    )




}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DrawerContent(
    scope: CoroutineScope,
    items: List<ItemNav>,
    selectedItem: MutableState<ItemNav>,
    drawerState: DrawerState
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
                icon = { Icon(item.Icono, contentDescription = null) },
                label = { Text(item.Descripcion) },
                selected = item == selectedItem.value,
                onClick = {
                    scope.launch { drawerState.close() }
                    selectedItem.value = item
                },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
        NavigationDrawerItem(
            icon = { Icon(Icons.Default.Favorite, contentDescription = null) },
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
fun TopBar(scope: CoroutineScope, selectedItem: MutableState<ItemNav>, drawerState: DrawerState) {
    CenterAlignedTopAppBar(
        title = {
            if (selectedItem.value.Descripcion == "Principal")
                Text(
                    selectedItem.value.Titulo,
                    fontFamily = DancingScript,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            else
                Text(
                    selectedItem.value.Titulo,
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