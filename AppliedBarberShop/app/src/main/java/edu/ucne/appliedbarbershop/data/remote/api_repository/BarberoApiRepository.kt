package edu.ucne.appliedbarbershop.data.remote.api_repository

import edu.ucne.appliedbarbershop.data.remote.api_dao.BarberoApi
import edu.ucne.appliedbarbershop.data.remote.dto.BarberoDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BarberoApiRepository @Inject constructor(
    private val api: BarberoApi
) {
    suspend fun getBarberos(): List<BarberoDto>{
        return withContext(Dispatchers.IO){
            val response = api.getAll()
            response.body()?: emptyList()
        }
    }

    suspend fun getBarbero(id:String?): BarberoDto? {
        return withContext(Dispatchers.IO){
            val response = api.getById(id ?: "")
            response.body()
        }
    }

    suspend fun insertBarbero(barbero: BarberoDto): BarberoDto? {
        return withContext(Dispatchers.IO){
            val response = api.insert(barbero)
            response.body()
        }
    }

    suspend fun deleteBarbero(id: String) : Boolean {
        return withContext(Dispatchers.IO){
            val response = api.delete(id)
            response.isSuccessful
        }
    }

    suspend fun updateBarbero(id: String, barbero: BarberoDto): BarberoDto?{
        return withContext(Dispatchers.IO){
            val response = api.update(id,barbero)
            response.body()
        }
    }
}