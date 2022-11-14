package edu.ucne.appliedbarbershop.data.remote.api_repository

import edu.ucne.appliedbarbershop.data.remote.api_dao.PerfilApi
import edu.ucne.appliedbarbershop.data.remote.dto.PerfilDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PerfilApiRepository @Inject constructor(
    private val api: PerfilApi
) {
    suspend fun getPerfiles(): List<PerfilDto>{
        return withContext(Dispatchers.IO){
            val response = api.getAll()
            response.body()?: emptyList()
        }
    }

    suspend fun getPerfil(id:String?): PerfilDto? {
        return withContext(Dispatchers.IO){
            val response = api.getById(id ?: "")
            response.body()
        }
    }

    suspend fun insertPerfil(perfil: PerfilDto): PerfilDto? {
        return withContext(Dispatchers.IO){
            val response = api.insert(perfil)
            response.body()
        }
    }

    suspend fun deletePerfil(id: String) : Boolean {
        return withContext(Dispatchers.IO){
            val response = api.delete(id)
            response.isSuccessful
        }
    }

    suspend fun updatePerfil(id: String, perfil: PerfilDto): PerfilDto?{
        return withContext(Dispatchers.IO){
            val response = api.update(id,perfil)
            response.body()
        }
    }
}