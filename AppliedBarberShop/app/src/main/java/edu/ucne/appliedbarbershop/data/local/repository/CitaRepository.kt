package edu.ucne.appliedbarbershop.data.local.repository

import edu.ucne.appliedbarbershop.data.local.AppDataBase
import edu.ucne.appliedbarbershop.data.local.models.Cita
import edu.ucne.appliedbarbershop.data.local.models.CitaCompleta
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CitaRepository @Inject constructor(
    private val db: AppDataBase
) {
    suspend fun insert(cita: Cita){
        db.citaDao.insert(cita)
    }

    suspend fun delete(cita: Cita){
        db.citaDao.delete(cita)
    }

    fun getAll(): Flow<List<CitaCompleta>>{
        return db.citaDao.getAll()
    }

    suspend fun getById(id:Int): CitaCompleta? {
        return db.citaDao.getById(id)
    }
}