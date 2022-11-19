package edu.ucne.appliedbarbershop.data.remote.api_repository

import edu.ucne.appliedbarbershop.data.remote.api_dao.CitaApi
import edu.ucne.appliedbarbershop.data.remote.dto.CitaDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.IOException
import javax.inject.Inject

class CitaApiRepository @Inject constructor(
    private val api: CitaApi
){
    suspend fun getCitas(): List<CitaDto>{
        try {
            val api = api.getAll();
            return api
        }catch (e: IOException){
            throw e
        }
    }

    suspend fun getCita(id:String?): CitaDto? {
        try {
            return this.api.getById(id ?: "0")
        } catch (e: IOException) {
            throw e
        }
    }

    suspend fun getCitasByClienteId(id: String?): List<CitaDto>{
        try {
            val api = api.getAllByClienteId(id ?: "0")
            return api
        }catch (e: IOException){
            throw e
        }
    }

    suspend fun insertCita(cita: CitaDto): CitaDto? {
        try {
            val api = api.insert(cita)
            return api
        } catch (e: IOException) {
            throw e
        }
    }

    suspend fun deleteCita(id: String) : Boolean {
        try {
            val api = api.delete(id)
            return true // debe verificar si se elimino
        } catch (e: IOException) {
            throw e
        }
    }

    suspend fun updateCita(id: String, cita: CitaDto): CitaDto? {
        try {
            val api = api.update(id,cita)
            return api
        } catch (e: IOException) {
            throw e
        }
    }
}