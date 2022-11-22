package edu.ucne.appliedbarbershop.data.remote.api_repository

import edu.ucne.appliedbarbershop.data.remote.api_dao.ServicioApi
import edu.ucne.appliedbarbershop.data.remote.dto.ServicioDto
import javax.inject.Inject

class ServicioApiRepository @Inject constructor(
    private val api: ServicioApi
) {
    suspend fun getServicios(): List<ServicioDto>{
        try {
            val api = api.getAll();
            return api
        }catch (e: Exception){
            println(e)
            return emptyList()
        }
    }

    suspend fun getServicio(id:String?): ServicioDto? {
        try {
            return this.api.getById(id ?: "0")
        } catch (e: Exception) {
            println(e)
            return null
        }
    }

    suspend fun getAllServiciosStatus(): List<ServicioDto>{
        try {
            return this.api.getAllStatus()
        } catch (e: Exception) {
            println(e)
            return emptyList()
        }
    }

    suspend fun insertServicio(servicio: ServicioDto): ServicioDto {
        try {
            return this.api.insert(servicio)
        } catch (e: Exception) {
            println(e)
            return servicio
        }
    }

    suspend fun deleteServicio(id: String) : Boolean {
        try {
            val api = api.delete(id)
            return true // debe verificar si se elimino
        } catch (e: Exception) {
            println(e)
            return false
        }
    }

    suspend fun updateServicio(id: String, servicio: ServicioDto): ServicioDto {
        try {
            return this.api.update(id, servicio)
        } catch (e: Exception) {
            println(e)
            return servicio
        }
    }
}