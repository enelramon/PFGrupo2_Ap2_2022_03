package edu.ucne.appliedbarbershop.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Servicios")
data class Servicio(
    @PrimaryKey(autoGenerate = true)
    var servicioId: Int,
    val nombre: String,
    val imagen: String?,
    val usuarioCreacionId: Int,
    val usuarioModificacionId: Int?,
    val status: Int
)
