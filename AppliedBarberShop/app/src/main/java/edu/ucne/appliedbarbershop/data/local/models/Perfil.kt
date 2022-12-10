package edu.ucne.appliedbarbershop.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Perfiles")
data class Perfil(
    @PrimaryKey
    val perfilId: Int
)

data class PerfilCompleto(
    val perfilId: Int,
    val clienteId: Int,
    val nombre: String,
    val apellido: String,
    val celular: String?,
    val fechaNacimiento: String?,
    val imagen: String?,
    val status: Int
)