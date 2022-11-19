package edu.ucne.appliedbarbershop.data.remote.api_repository

import edu.ucne.appliedbarbershop.data.remote.api_dao.PerfilApi
import edu.ucne.appliedbarbershop.data.remote.dto.PerfilDto
import okio.IOException
import javax.inject.Inject

class PerfilApiRepository @Inject constructor(
    private val api: PerfilApi
) {
    suspend fun getPerfiles(): List<PerfilDto>{
        try {
            val api = api.getAll();
            return api
        }catch (e: IOException){
            throw e
        }
    }

    suspend fun getPerfil(id:String?): PerfilDto {
        try {
            return this.api.getById(id ?: "0")
        } catch (e: IOException) {
            throw e
        }
    }

    suspend fun getAllPerfilStatus(id:String): List<PerfilDto> {
        try {
            return this.api.getAllStatus()
        } catch (e: IOException) {
            throw e
        }
    }

    suspend fun insertPerfil(perfil: PerfilDto): PerfilDto {
        try {
            return this.api.insert(perfil)
        } catch (e: IOException) {
            throw e
        }
    }

    suspend fun deletePerfil(id: String) : Boolean {
        try {
            val api = api.delete(id)
            return true // debe verificar si se elimino
        } catch (e: IOException) {
            throw e
        }
    }

    suspend fun updatePerfil(id: String, perfil: PerfilDto): PerfilDto {
        try {
            return this.api.update(id, perfil)
        } catch (e: IOException) {
            throw e
        }
    }
}