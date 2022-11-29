package edu.ucne.appliedbarbershop.data.local.repository

import edu.ucne.appliedbarbershop.data.local.AppDataBase
import edu.ucne.appliedbarbershop.data.local.models.Entorno
import edu.ucne.appliedbarbershop.data.local.models.Servicio
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EntornoRepository @Inject constructor(
    private val db: AppDataBase
) {

    suspend fun insert(entorno: Entorno){
        db.entornoDao.insert(entorno)
    }

    suspend fun getEntorno(): Flow<Entorno?> {
        return db.entornoDao.getEntorno()
    }
}