package edu.ucne.appliedbarbershop.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Entorno")
data class Entorno(
    @PrimaryKey
    val entornoId: Int,
    val codigoBarberia: String,
    val clienteIdSeleccionado: Int,
    val isBarberoAutenticado: Boolean
)
