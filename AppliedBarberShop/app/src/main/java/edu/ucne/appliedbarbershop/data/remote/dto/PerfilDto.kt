package edu.ucne.appliedbarbershop.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PerfilDto(
    val perfilId: Int,
    val nombre: String,
    val apellido: String,
    val celular: String?,
    val fechaNacimiento: String?,
    val imagen: String?,
    val status: Int
)
