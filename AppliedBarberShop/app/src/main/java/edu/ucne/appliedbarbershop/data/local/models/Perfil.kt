package edu.ucne.appliedbarbershop.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Perfiles")
data class Perfil(
    @PrimaryKey(autoGenerate = true)
    val perfilId: Int,
    val nombre: String,
    val apellido: String,
    val celular: String?,
    val fechaNacimiento: String?,
    val imagen: String?,
    val status: Int
)
