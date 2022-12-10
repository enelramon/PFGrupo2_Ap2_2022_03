package edu.ucne.appliedbarbershop.data.remote.dto

import com.squareup.moshi.JsonClass
import java.time.LocalDate
import java.time.LocalDateTime

@JsonClass(generateAdapter = true)
data class CitaDto(
    val citaId: Int,
    val servicioId: Int,
    val barberoId: Int,
    val clienteId: Int,
    val fecha: String,
    val mensaje: String?,
    val fechaCreacion: String? = LocalDateTime.now().toString(),
    val fechaModificacion: String? = LocalDateTime.now().toString(),
    val usuarioCreacionId: Int,
    val usuarioModificacionId: Int?,
    val status: Int
)
