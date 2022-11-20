package edu.ucne.appliedbarbershop.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ClienteDto(
    val clienteId: Int,
    val nombre: String,
    val apellido: String,
    val celular: String?,
    val fechaNacimiento: String?,
    val imagen: String?,
    val status: Int
)
