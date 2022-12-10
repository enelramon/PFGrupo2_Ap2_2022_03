package edu.ucne.appliedbarbershop.data.local.models

import androidx.room.*

@Entity(tableName = "Citas")
data class Cita(
    @PrimaryKey(autoGenerate = true)
    val citaId: Int,
    val servicioId: Int,
    val barberoId: Int,
    val clienteId: Int,
    val fecha: String,
    val mensaje: String?,
    val usuarioCreacionId: Int,
    val usuarioModificacionId: Int?,
    val status: Int
)

data class CitaCompleta(
    val citaId: Int,
    val servicioId: Int,
    val servicioNombre: String?,
    val barberoId: Int,
    val clienteId: Int,
    val fecha: String,
    val mensaje: String?,
    val usuarioCreacionId: Int,
    val usuarioModificacionId: Int?,
    val status: Int,
    val barberoNombre: String?,
    val barberoApellido: String?,
    val barberoCelular: String?,
    val barberoImagen: String?,
    val clienteNombre: String?,
    val clienteApellido: String?,
    val clienteCelular: String?,
    val clienteImagen: String?
)
