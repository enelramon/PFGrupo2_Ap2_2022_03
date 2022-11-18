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
            val api = api.getAll()
            api.body()?: emptyList()
        }
    }

    suspend fun getBarbero(id:String?): BarberoDto? {
        return withContext(Dispatchers.IO){
            val api = api.getById(id ?: "")
            api.body()
        }
    }

    suspend fun insertBarbero(barbero: BarberoDto): BarberoDto? {
        return withContext(Dispatchers.IO){
            val api = api.insert(barbero)
            api.body()
        }
    }

    suspend fun deleteBarbero(id: String) : Boolean {
        return withContext(Dispatchers.IO){
            val api = api.delete(id)
            api.isSuccessful
        }
    }

    suspend fun updateBarbero(id: String, barbero: BarberoDto): BarberoDto?{
        return withContext(Dispatchers.IO){
            val api = api.update(id,barbero)
            api.body()
        }
    }
}