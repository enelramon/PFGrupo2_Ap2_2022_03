package edu.ucne.appliedbarbershop.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ServicioDto(
    val servicioId: Int,
    val nombre: String,
    val imagen: String?,
    val usuarioCreacionId: Int,
    val usuarioModificacionId: Int?,
    val status: Int
)
