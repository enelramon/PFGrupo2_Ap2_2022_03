package edu.ucne.appliedbarbershop

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.ucne.appliedbarbershop.ui.theme.DancingScript
import kotlinx.coroutines.launch

private class ItemNav(
    val Descripcion: String,
    val Icono: ImageVector,
    val Titulo: String = Descripcion
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaginaPrincipal() {

    val activity = (LocalContext.current as? Activity)

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val items = listOf(
        ItemNav("Principal", Icons.Default.Favorite, "Applied BarberShop"),
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

@Preview
@Composable
fun PreviewPaginaPrincipal() {
    PaginaPrincipal()
}