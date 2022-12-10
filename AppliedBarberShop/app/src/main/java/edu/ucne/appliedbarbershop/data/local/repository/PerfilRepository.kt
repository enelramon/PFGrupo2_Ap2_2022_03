package edu.ucne.appliedbarbershop.data.local.repository

import edu.ucne.appliedbarbershop.data.local.AppDataBase
import edu.ucne.appliedbarbershop.data.local.models.Perfil
import edu.ucne.appliedbarbershop.data.local.models.PerfilCompleto
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

    suspend fun getAll(): Flow<List<PerfilCompleto>>{
       return db.perfilDao.getAll()
    }

    suspend fun getById(id: Int): Flow<PerfilCompleto?> {
        return db.perfilDao.getById(id)
    }

}