package edu.ucne.appliedbarbershop.data.local.repository

import edu.ucne.appliedbarbershop.data.local.AppDataBase
import edu.ucne.appliedbarbershop.data.local.models.Barbero
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BarberoRepository @Inject constructor(
    private val db: AppDataBase
) {

    suspend fun insert(barbero: Barbero){
        db.barberoDao.insert(barbero)
    }

    suspend fun delete(barbero: Barbero){
        db.barberoDao.delete(barbero)
    }

    fun getAll(): Flow<List<Barbero>>{
       return db.barberoDao.getAll()
    }

    suspend fun getById(id: Int): Barbero? {
        return db.barberoDao.getById(id)
    }
}