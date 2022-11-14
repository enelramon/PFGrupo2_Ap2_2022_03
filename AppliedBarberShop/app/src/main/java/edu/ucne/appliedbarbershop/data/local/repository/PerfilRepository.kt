package edu.ucne.appliedbarbershop.data.local.repository

import edu.ucne.appliedbarbershop.data.local.AppDataBase
import edu.ucne.appliedbarbershop.data.local.models.Perfil
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PerfilRepository @Inject constructor(
    private val db: AppDataBase
) {

    suspend fun insert(perfil: Perfil){
        db.perfilDao.insert(perfil)
    }

    suspend fun delete(perfil: Perfil){
        db.perfilDao.delete(perfil)
    }

    fun getAll(): Flow<List<Perfil>> {
        return db.perfilDao.getAll()
    }

    suspend fun getById(id: Int): Perfil? {
        return db.perfilDao.getById(id)
    }
}