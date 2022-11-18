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
            val api = api.getAll()
            api.body()?: emptyList()
        }
    }

    suspend fun getPerfil(id:String?): PerfilDto? {
        return withContext(Dispatchers.IO){
            val api = api.getById(id ?: "")
            api.body()
        }
    }

    suspend fun insertPerfil(perfil: PerfilDto): PerfilDto? {
        return withContext(Dispatchers.IO){
            val api = api.insert(perfil)
            api.body()
        }
    }

    suspend fun deletePerfil(id: String) : Boolean {
        return withContext(Dispatchers.IO){
            val api = api.delete(id)
            api.isSuccessful
        }
    }

    suspend fun updatePerfil(id: String, perfil: PerfilDto): PerfilDto?{
        return withContext(Dispatchers.IO){
            val api = api.update(id,perfil)
            api.body()
        }
    }
}