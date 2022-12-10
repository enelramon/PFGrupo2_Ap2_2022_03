package edu.ucne.appliedbarbershop.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.appliedbarbershop.data.local.dao.*
import edu.ucne.appliedbarbershop.data.local.models.*

@Database(
    entities = [
        Barbero::class,
        Cita::class,
        Cliente::class,
        Servicio::class,
        Entorno::class,
        Perfil::class
    ],
    version = 2,
    exportSchema = false
)

abstract class AppDataBase : RoomDatabase() {
    abstract val barberoDao: BarberoDao
    abstract val citaDao: CitaDao
    abstract val clienteDao: ClienteDao
    abstract val servicioDao: ServicioDao
    abstract val entornoDao: EntornoDao
    abstract val perfilDao: PerfilDao
}