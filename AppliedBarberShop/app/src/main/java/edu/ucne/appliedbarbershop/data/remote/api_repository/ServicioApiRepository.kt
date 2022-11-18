package edu.ucne.appliedbarbershop.data.remote.api_repository

import edu.ucne.appliedbarbershop.data.remote.api_dao.ServicioApi
import edu.ucne.appliedbarbershop.data.remote.dto.ServicioDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ServicioApiRepository @Inject constructor(
    private val api: ServicioApi
) {
    suspend fun getServicios(): List<ServicioDto>{
        return withContext(Dispatchers.IO){
            val api = api.getAll()
            api.body()?: emptyList()
        }
    }

    suspend fun getServicio(id:String?): ServicioDto? {
        return withContext(Dispatchers.IO){
            val api = api.getById(id ?: "")
            api.body()
        }
    }

    suspend fun insertServicio(servicio: ServicioDto): ServicioDto? {
        return withContext(Dispatchers.IO){
            val api = api.insert(servicio)
            api.body()
        }
    }

    suspend fun deleteServicio(id: String) : Boolean {
        return withContext(Dispatchers.IO){
            val api = api.delete(id)
            api.isSuccessful
        }
    }

    suspend fun updateServicio(id: String, servicio: ServicioDto): ServicioDto?{
        return withContext(Dispatchers.IO){
            val api = api.update(id, servicio)
            api.body()
        }
    }
}