package edu.ucne.appliedbarbershop.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CitaDto(
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
