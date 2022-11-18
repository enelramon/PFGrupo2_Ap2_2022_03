package edu.ucne.appliedbarbershop.data.remote.api_repository

import edu.ucne.appliedbarbershop.data.remote.api_dao.CitaApi
import edu.ucne.appliedbarbershop.data.remote.dto.CitaDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CitaApiRepository @Inject constructor(
    private val api: CitaApi
){
    suspend fun getCitas(): List<CitaDto>{
        return withContext(Dispatchers.IO){
            val api = api.getAll()
            api.body()?: emptyList()
        }
    }

    suspend fun getCita(id:String?): CitaDto?{
        return withContext(Dispatchers.IO){
            val api = api.getById(id ?: "")
            api.body()
        }
    }

    suspend fun insertCita(cita: CitaDto): CitaDto? {
        return withContext(Dispatchers.IO){
            val api = api.insert(cita)
            api.body()
        }
    }

    suspend fun deleteCita(id: String) : Boolean {
        return withContext(Dispatchers.IO){
            val api = api.delete(id)
            api.isSuccessful
        }
    }

    suspend fun updateCita(id: String, cita: CitaDto): CitaDto? {
        return withContext(Dispatchers.IO){
            val api = api.update(id, cita)
            api.body()
        }
    }
}