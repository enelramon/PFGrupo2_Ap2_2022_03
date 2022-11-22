package edu.ucne.appliedbarbershop.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.appliedbarbershop.data.local.dao.BarberoDao
import edu.ucne.appliedbarbershop.data.local.dao.CitaDao
import edu.ucne.appliedbarbershop.data.local.dao.ClienteDao
import edu.ucne.appliedbarbershop.data.local.dao.ServicioDao
import edu.ucne.appliedbarbershop.data.local.models.Barbero
import edu.ucne.appliedbarbershop.data.local.models.Cliente
import edu.ucne.appliedbarbershop.data.local.models.Servicio
import edu.ucne.appliedbarbershop.data.local.models.Cita

@Database(
    entities = [
        Barbero::class,
        Cita::class,
        Cliente::class,
        Servicio::class,
    ],
    version = 2,
    exportSchema = false
)

abstract class AppDataBase : RoomDatabase() {
    abstract val barberoDao: BarberoDao
    abstract val citaDao: CitaDao
    abstract val clienteDao: ClienteDao
    abstract val servicioDao: ServicioDao
}