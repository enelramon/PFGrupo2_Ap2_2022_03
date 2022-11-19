package edu.ucne.appliedbarbershop.data.remote.api_repository

import edu.ucne.appliedbarbershop.data.remote.api_dao.CitaApi
import edu.ucne.appliedbarbershop.data.remote.dto.CitaDto
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

    suspend fun getCita(id:String?): CitaDto {
        try {
            return this.api.getById(id ?: "")
        } catch (e: IOException) {
            throw e
        }
    }

    suspend fun getCitasByClienteId(id: String?): List<CitaDto>{
        try {
            return this.api.getAllByClienteId(id ?: "")
        } catch (e: IOException) {
            throw e
        }
    }

    suspend fun insertCita(cita: CitaDto): CitaDto {
        try {
            return this.api.insert(cita)
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

    suspend fun updateCita(id: String, cita: CitaDto): CitaDto {
        try {
            return this.api.update(id, cita)
        } catch (e: IOException) {
            throw e
        }
    }
}