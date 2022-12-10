package edu.ucne.appliedbarbershop.utils.Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.ucne.appliedbarbershop.ui.theme.Realizada
import edu.ucne.appliedbarbershop.ui.theme.Rechazada


@Composable
fun CardComponent(
    titulo: String,
    cuerpo: String? = null,
    subtitulo: String? = null,
    status: Int = 0,
    mensaje: String? = null,
    btn1Nombre: String? = null,
    btn2Nombre: String? = null,
    btn1OnClick: () -> Unit? = {},
    btn2OnClick: () -> Unit? = {},
    onClick: () -> Unit? = {},
    selected: Boolean = false,
    btnEnabled: Boolean = true,
    clickeable: Boolean = false,
) {
    var bgColor = MaterialTheme.colorScheme.surfaceVariant
    if (selected) bgColor = MaterialTheme.colorScheme.primaryContainer
    if (status == 2) bgColor = Realizada
    if (status == 3) bgColor = Rechazada

    var modifier = if (clickeable) Modifier
        .fillMaxWidth()
        .clickable { onClick() }
    else Modifier
        .fillMaxWidth()

    Card(
        border = BorderStroke(1.dp, Color(0, 0, 0, 12)),
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = bgColor
        )
    ) {
        CardHeaderComponent(titulo, subtitulo)

        if (cuerpo != null)
            CardBodyComponent(text = cuerpo, status, mensaje)

        if ((btn1Nombre != null) || (btn2Nombre != null))
            CardFooterComponent(
                btn1Nombre = btn1Nombre,
                btn2Nombre = btn2Nombre,
                btn1OnClick = btn1OnClick,
                btn2OnClick = btn2OnClick,
                btnEnabled = btnEnabled
            )
    }
    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
fun CardHeaderComponent(titulo: String, subtitulo: String?) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = titulo,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )
        if (subtitulo != null && !subtitulo.isBlank())
            Text(
                text = subtitulo ?: "",
                fontSize = 14.sp
            )
    }
}

@Composable
fun CardBodyComponent(text: String, status: Int, mensaje: String?) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = text)
        Spacer(modifier = Modifier.height(16.dp))
        if (status == 3) {
            Text(
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                text = "Rechazada"
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                fontWeight = FontWeight.Bold,
                text = mensaje ?: ""
            )
        }
    }
}

@Composable
fun CardFooterComponent(
    btn1Nombre: String?,
    btn1OnClick: () -> Unit?,
    btn2Nombre: String?,
    btn2OnClick: () -> Unit?,
    btnEnabled: Boolean = true
) {
    Row(modifier = Modifier.padding(16.dp)) {
        if (btn1Nombre != null) {
            Button(onClick = { btn1OnClick() }, enabled = btnEnabled) {
                Text(text = btn1Nombre)
            }
        }
        if (btn2Nombre != null) {
            Spacer(modifier = Modifier.width(12.dp))
            Button(onClick = { btn2OnClick() }, enabled = btnEnabled) {
                Text(text = btn2Nombre)
            }
        }
    }
}