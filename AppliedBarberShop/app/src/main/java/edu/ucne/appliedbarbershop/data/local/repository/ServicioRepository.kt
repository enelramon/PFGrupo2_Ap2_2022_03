package edu.ucne.appliedbarbershop.data.local.repository

import edu.ucne.appliedbarbershop.data.local.AppDataBase
import edu.ucne.appliedbarbershop.data.local.models.Servicio
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ServicioRepository @Inject constructor(
    private val db: AppDataBase
) {

    suspend fun insert(servicio: Servicio){
        db.servicioDao.insert(servicio)
    }

    suspend fun delete(servicio: Servicio){
        db.servicioDao.delete(servicio)
    }

    suspend fun getAll(): Flow<List<Servicio>> {
        return db.servicioDao.getAll()
    }

    suspend fun truncateTable() {
        return db.barberoDao.truncateTable()
    }
}