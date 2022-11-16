package edu.ucne.appliedbarbershop.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.appliedbarbershop.data.local.dao.BarberoDao
import edu.ucne.appliedbarbershop.data.local.dao.CitaDao
import edu.ucne.appliedbarbershop.data.local.dao.PerfilDao
import edu.ucne.appliedbarbershop.data.local.dao.ServicioDao
import edu.ucne.appliedbarbershop.data.local.models.Barbero
import edu.ucne.appliedbarbershop.data.local.models.Perfil
import edu.ucne.appliedbarbershop.data.local.models.Servicio
import edu.ucne.appliedbarbershop.data.local.models.Cita
import edu.ucne.appliedbarbershop.data.local.repository.BarberoRepository
import edu.ucne.appliedbarbershop.data.local.repository.CitaRepository
import edu.ucne.appliedbarbershop.data.local.repository.PerfilRepository
import edu.ucne.appliedbarbershop.data.local.repository.ServicioRepository

@Database(
    entities = [
        Barbero::class,
        Cita::class,
        Perfil::class,
        Servicio::class,
    ],
    version = 1,
    exportSchema = false
)

abstract class AppDataBase : RoomDatabase() {
    abstract val barberoDao: BarberoDao
    abstract val citaDao: CitaDao
    abstract val perfilDao: PerfilDao
    abstract val servicioDao: ServicioDao
}