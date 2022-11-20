package edu.ucne.appliedbarbershop

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.ucne.appliedbarbershop.ui.clientees.PerfilViewModel
import edu.ucne.appliedbarbershop.ui.theme.DancingScript
import edu.ucne.appliedbarbershop.ui.theme.Lobster
import edu.ucne.appliedbarbershop.utils.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmaRegistroPerfilScreen(
    navController: NavController,
    viewModel: PerfilViewModel = hiltViewModel()
) {
    Scaffold(
        bottomBar = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(bottom = 100.dp)
                    .fillMaxWidth()
            ) {
                Button(
                    onClick = { navController.navigate(Screen.PrincipalScreen.Route) }) {
                    Text(text = "Iniciemos")
                }
            }
        }
    ) {
        it
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Column() {
                    Spacer(modifier = Modifier.height(39.dp))
                    Text(
                        text = "Excelente\n{Nombre}!",
                        textAlign = TextAlign.Center,
                        lineHeight = 56.sp,
                        fontSize = 56.sp,
                        fontFamily = Lobster,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = buildAnnotatedString {
                            append("Con ")
                            withStyle(style = SpanStyle(fontFamily = DancingScript)) {
                                append("Applied Barbershop")
                            }
                            append(" puedes hacer reservaciones con tu barbero de confianza.")
                        },
                        textAlign = TextAlign.Center,
                        lineHeight = 56.sp,
                        fontSize = 56.sp,
                        modifier = Modifier.fillMaxWidth().padding(42.dp)
                    )
                }

            }
            Spacer(modifier = Modifier.height(87.dp))
        }
    }
}