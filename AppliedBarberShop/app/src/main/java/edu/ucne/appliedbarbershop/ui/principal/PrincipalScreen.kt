package edu.ucne.appliedbarbershop.ui.principal

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import edu.ucne.appliedbarbershop.ui.theme.DancingScript
import edu.ucne.appliedbarbershop.utils.Constantes
import kotlinx.coroutines.launch

private class ItemNav(
    val Descripcion: String,
    val Icono: ImageVector,
    val Titulo: String = Descripcion
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrincipalScreen(
    navController: NavController
) {

    val activity = (LocalContext.current as? Activity)

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
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
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
        },
        content = {
            Scaffold(
                topBar = {
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
                },
                content = {
                    it

                }
            )
        }
    )
}