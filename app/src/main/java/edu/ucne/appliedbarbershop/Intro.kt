package edu.ucne.appliedbarbershop

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.ucne.appliedbarbershop.ui.theme.DancingScript

@Composable
fun Intro() {
    Scaffold() {
        it
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(39.dp))
            Text(
                text = "Applied BarberShop",
                textAlign = TextAlign.Center,
                lineHeight = 56.sp,
                fontSize = 56.sp,
                fontFamily = DancingScript,
                modifier = Modifier.width(312.dp)
            )
            Spacer(modifier = Modifier.height(38.dp))
            Image(
                painter = painterResource(id = R.drawable.introbarbershop),
                contentDescription = "Imagen Intro",
                modifier = Modifier.fillMaxWidth().background(Color.Red)
            )
        }
    }

}

@Preview
@Composable
fun PreviewIntro() {
    Intro()
}