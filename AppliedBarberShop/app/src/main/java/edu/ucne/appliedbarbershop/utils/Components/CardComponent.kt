package edu.ucne.appliedbarbershop.utils.Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CardComponent(
    titulo: String,
    cuerpo: String? = null,
    subtitulo: String? = null,
    btn1Nombre: String? = null,
    btn2Nombre: String? = null,
    btn1OnClick: () -> Unit? = {},
    btn2OnClick: () -> Unit? = {},
    onClick: () -> Unit? = {},
    selected: Boolean = false
) {
    Card(
        border = BorderStroke(1.dp, Color(0, 0, 0, 12)),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .background(if (selected) Color.DarkGray else Color.Transparent)
    ) {
        CardHeaderComponent(titulo, subtitulo)
        if (cuerpo != null) CardBodyComponent(text = cuerpo)
        if ((btn1Nombre != null) || (btn2Nombre != null))
            CardFooterComponent(
                btn1Nombre = btn1Nombre,
                btn2Nombre = btn2Nombre,
                btn1OnClick = btn1OnClick,
                btn2OnClick = btn2OnClick
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
fun CardBodyComponent(text: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = text)
    }
}

@Composable
fun CardFooterComponent(
    btn1Nombre: String?,
    btn1OnClick: () -> Unit?,
    btn2Nombre: String?,
    btn2OnClick: () -> Unit?
) {
    Row(modifier = Modifier.padding(16.dp)) {
        if (btn1Nombre != null) {
            Button(onClick = { btn1OnClick() }) {
                Text(text = btn1Nombre)
            }
        }
        if (btn2Nombre != null) {
            Spacer(modifier = Modifier.width(12.dp))
            Button(onClick = { btn2OnClick() }) {
                Text(text = btn2Nombre)
            }
        }
    }
}