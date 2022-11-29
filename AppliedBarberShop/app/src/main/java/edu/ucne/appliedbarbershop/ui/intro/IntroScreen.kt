package edu.ucne.appliedbarbershop

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.ucne.appliedbarbershop.ui.intro.IntroViewModel
import edu.ucne.appliedbarbershop.ui.navegacion.NavegacionViewModel
import edu.ucne.appliedbarbershop.ui.theme.DancingScript
import edu.ucne.appliedbarbershop.utils.Constantes
import edu.ucne.appliedbarbershop.utils.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntroScreen(
    navController: NavController,
    navegacionViewModel: NavegacionViewModel,
    viewModel: IntroViewModel = hiltViewModel()
) {
    Scaffold(
        bottomBar = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(bottom = 100.dp)
                    .fillMaxWidth()
            ) {
                if (navegacionViewModel.sincronizacionCliente) {
                    if (navegacionViewModel.cliente.clienteId > 0)
                        Button(
                            onClick = { navController.navigate(Screen.PrincipalScreen.Route) }) {
                            Text(text = "Acceder")
                        }
                    else
                        Button(
                            onClick = { navController.navigate(Screen.RegistroPerfilScreen.Route) }) {
                            Text(text = "Vamos a crear tu perfil")
                        }
                }
            }
        }
    ) {
        it
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(39.dp))
            Text(
                text = Constantes.NombreEmpresa.value,
                textAlign = TextAlign.Center,
                lineHeight = 56.sp,
                fontSize = 56.sp,
                fontFamily = DancingScript,
                modifier = Modifier.width(312.dp)
            )
            Spacer(modifier = Modifier.height(38.dp))
            Image(
                painter = painterResource(id = R.drawable.introbarbershop),
                contentDescription = "Imagen Intro"
            )
        }
    }

}