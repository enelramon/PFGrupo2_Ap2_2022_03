package edu.ucne.appliedbarbershop.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Barberos")
data class Barbero(
    @PrimaryKey(autoGenerate = true)
    var barberoId: Int,
    val nombre: String,
    val apellido: String,
    val celular: String?,
    val fechaNacimiento: String?,
    val imagen: String?,
    val status: Int
)
