package edu.ucne.appliedbarbershop

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.ucne.appliedbarbershop.data.remote.dto.CitaDto
import edu.ucne.appliedbarbershop.ui.citas.CitaViewModel
import edu.ucne.appliedbarbershop.ui.navegacion.NavegacionViewModel
import edu.ucne.appliedbarbershop.ui.navegacion.TopBar
import edu.ucne.appliedbarbershop.utils.Components.CardComponent
import edu.ucne.appliedbarbershop.utils.Screen
import kotlinx.coroutines.CoroutineScope
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsultaMisCitasScreen(
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
        },
        floatingActionButton = {
            Button(onClick = { navController.navigate(Screen.RegistroCita_BarberoScreen.Route) }) {
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
                items(navegacionViewModel.citas) {
                    var localDateTime = LocalDateTime.parse(it.fecha)
//                    Text(text = "dayOfWeek: " + localDateTime.dayOfWeek.toString())
//                    Text(text = "dayOfMonth: " + localDateTime.dayOfMonth.toString())
//                    Text(text = "dayOfYear: " + localDateTime.dayOfYear.toString())
//                    Text(text = "hour: " + localDateTime.hour.toString())
//                    Text(text = "minute: " + localDateTime.minute.toString())
//                    Text(text = "month: " + localDateTime.month.toString())
//                    Text(text = "monthValue: " + localDateTime.monthValue.toString())
//                    Text(text = "nano: " + localDateTime.nano.toString())
//                    Text(text = "second: " + localDateTime.second.toString())
//                    Text(text = "year: " + localDateTime.year.toString())
                    if (it.status == 1 || it.status == 3)
                        CardComponent(
                            titulo = (it.barberoNombre + " " + it.barberoApellido) ?: "",
                            subtitulo = it.barberoCelular ?: "",
                            cuerpo = separarFecha(it.fecha) + "\n" + "Servicio: " + it.servicioNombre,
                            status = it.status,
                            mensaje = it.mensaje,
                            btn1Nombre = "Editar",
                            btn2Nombre = "Eliminar",
                            btn1OnClick = { navController.navigate(Screen.RegistroCita_BarberoScreen.Route + "/" + it.citaId.toString()) },
                            btn2OnClick = {viewModel.deleteCita(
                                cita = CitaDto(
                                    it.citaId,
                                    it.servicioId,
                                    it.barberoId,
                                    it.clienteId,
                                    it.fecha,
                                    it.mensaje,
                                    LocalDateTime.now().toString(), // No se debe modificar
                                    LocalDateTime.now().toString(),
                                    it.usuarioCreacionId,
                                    it.usuarioModificacionId,
                                    0
                                )
                            )}
                        )
                    else
                        CardComponent(
                            titulo = (it.barberoNombre + " " + it.barberoApellido) ?: "",
                            subtitulo = it.barberoCelular ?: "",
                            cuerpo = it.fecha,
                            status = it.status,
                            mensaje = it.mensaje
                        )
                }
            }
        }
    }
}

fun separarFecha(fecha: String) : String{
    val delim = "T"
    val palabras = fecha.split(delim)
    var aux = ""

    aux = "Fecha: " + palabras[0] + "\n" + separarHora(palabras[1])

    return aux
}

fun separarHora(hora: String) : String{
    val delim = ":"
    val tiempo = hora.split(delim)
    var aux = ""

    aux = "Hora: " + tiempo[0] + ":" + tiempo[1]

    return aux
}