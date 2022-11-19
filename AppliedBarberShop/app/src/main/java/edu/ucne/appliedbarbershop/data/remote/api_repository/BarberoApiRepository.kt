package edu.ucne.appliedbarbershop.data.remote.api_repository

import edu.ucne.appliedbarbershop.data.remote.api_dao.BarberoApi
import edu.ucne.appliedbarbershop.data.remote.dto.BarberoDto
import okio.IOException
import javax.inject.Inject

class BarberoApiRepository @Inject constructor(
    private val api: BarberoApi
) {
    suspend fun getBarberos(): List<BarberoDto> {
        try {
            val api = api.getAll();
            return api
        }catch (e: IOException){
            throw e
        }
    }

    suspend fun getBarbero(id:String?): BarberoDto {
        try {
            return this.api.getById(id ?: "")
        } catch (e: IOException) {
            throw e
        }
    }

    suspend fun getAllBarberosStatus(id:String): List<BarberoDto> {
        try {
            return this.api.getAllStatus()
        } catch (e: IOException) {
            throw e
        }
    }

    suspend fun insertBarbero(barbero: BarberoDto): BarberoDto {
        try {
            return this.api.insert(barbero)
        } catch (e: IOException) {
            throw e
        }
    }

    suspend fun deleteBarbero(id: String) : Boolean {
        try {
            val api = api.delete(id)
            return true // debe verificar si se elimino
        } catch (e: IOException) {
            throw e
        }
    }

    suspend fun updateBarbero(id: String, barbero: BarberoDto): BarberoDto {
        try {
            return this.api.update(id, barbero)
        } catch (e: IOException) {
            throw e
        }
    }

}