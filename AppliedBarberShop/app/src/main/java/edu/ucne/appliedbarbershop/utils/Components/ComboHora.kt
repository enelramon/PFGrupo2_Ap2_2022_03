package edu.ucne.appliedbarbershop.utils.Components

import android.app.TimePickerDialog
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComboHora(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {

    val calendario = Calendar.getInstance()
    val Hora = calendario.get(Calendar.HOUR)
    val Minuto = calendario.get(Calendar.MINUTE)

    val contexto = LocalContext.current

    val timePickerDialog = TimePickerDialog(
        contexto, { d, Hora, Minuto ->
            run {
                var h = if (Hora.toString().count() == 1) "0" + Hora else Hora
                var m = if (Minuto.toString().count() == 1) "0" + Minuto else Minuto
                onValueChange("$h:$m:00")
            }
        }, Hora, Minuto, false
    )

    val interactionSourceHora = remember { MutableInteractionSource() }
    val isPressedHora: Boolean by interactionSourceHora.collectIsPressedAsState()
    if (isPressedHora) timePickerDialog.show()

    OutlinedTextField(
        readOnly = true,
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text(text = label) },
        interactionSource = interactionSourceHora,
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Schedule,
                contentDescription = null
            )
        }
    )
}
