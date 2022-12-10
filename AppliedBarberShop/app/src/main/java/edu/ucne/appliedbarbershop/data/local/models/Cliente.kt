package edu.ucne.appliedbarbershop.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Clientes")
data class Cliente(
    @PrimaryKey(autoGenerate = true)
    val clienteId: Int,
    val nombre: String,
    val apellido: String,
    val celular: String?,
    val fechaNacimiento: String?,
    val imagen: String?,
    val status: Int
)
