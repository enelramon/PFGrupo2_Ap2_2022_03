package edu.ucne.appliedbarbershop.utils.Components

import android.app.DatePickerDialog
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComboFecha(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {

    val calendario = Calendar.getInstance()
    val Año = calendario.get(Calendar.YEAR)
    val Mes = calendario.get(Calendar.MONTH)
    val Dia = calendario.get(Calendar.DAY_OF_MONTH)

    val contexto = LocalContext.current

    val datePickerDialog = DatePickerDialog(
        contexto,
        { d, Año, Mes, Dia ->
            val mes = Mes + 1
            val m = if (mes.toString().count() == 1) "0" + mes else mes
            val dia = if (Dia.toString().count() == 1) "0" + Dia else Dia
            onValueChange("$Año-$m-$dia")
        }, Año, Mes, Dia
    )

    val interactionSourceFecha = remember { MutableInteractionSource() }
    val isPressedFecha: Boolean by interactionSourceFecha.collectIsPressedAsState()
    if (isPressedFecha) datePickerDialog.show()

    OutlinedTextField(
        readOnly = true,
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text(text = label) },
        interactionSource = interactionSourceFecha,
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = null
            )
        }
    )
}
