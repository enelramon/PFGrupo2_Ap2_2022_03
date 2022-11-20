package edu.ucne.appliedbarbershop.data.remote.api_repository

import edu.ucne.appliedbarbershop.data.remote.api_dao.ClienteApi
import edu.ucne.appliedbarbershop.data.remote.dto.ClienteDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ClienteApiRepository @Inject constructor(
    private val api: ClienteApi
) {
    suspend fun getClientes(): List<ClienteDto> {
        return withContext(Dispatchers.IO) {
            val response = api.getAll()
            response.body() ?: emptyList()
        }
    }

    suspend fun getPerfil(id: String?): ClienteDto? {
        return withContext(Dispatchers.IO) {
            val response = api.getById(id ?: "")
            response.body()
        }
    }

    // TODO poner todos los metodos en un bloque try catch
    suspend fun insertPerfil(cliente: ClienteDto): Boolean {
        try {
            return withContext(Dispatchers.IO) {
                val response = api.insert(cliente)
                response.isSuccessful
            }
        } catch (e: Exception) {
            println(e.message)
            return false
        }
    }

    suspend fun deletePerfil(id: String): Boolean {
        return withContext(Dispatchers.IO) {
            val response = api.delete(id)
            response.isSuccessful
        }
    }

    suspend fun updatePerfil(id: String, cliente: ClienteDto): ClienteDto? {
        return withContext(Dispatchers.IO) {
            val response = api.update(id, cliente)
            response.body()
        }
    }
}