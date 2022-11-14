package edu.ucne.appliedbarbershop.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Citas")
data class Cita(
    @PrimaryKey(autoGenerate = true)
    val citaId: Int,
    val servicioId: Int,
    val barberoId: Int,
    val perfilId: Int,
    val fecha: String,
    val mensaje: String,
    val usuarioCreacionId: Int,
    val usuarioModificacionId: Int?,
    val status: Int
)
