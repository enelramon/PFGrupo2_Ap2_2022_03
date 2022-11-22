package edu.ucne.appliedbarbershop.data.remote.api_repository

import edu.ucne.appliedbarbershop.data.remote.api_dao.ClienteApi
import edu.ucne.appliedbarbershop.data.remote.dto.ClienteDto
import javax.inject.Inject

class ClienteApiRepository @Inject constructor(
    private val api: ClienteApi
) {
    suspend fun getClientes(): List<ClienteDto>{
        try {
            val api = api.getAll();
            return api
        }catch (e: Exception){
            println(e)
            return emptyList()
        }
    }

    suspend fun getCliente(id:String?): ClienteDto? {
        try {
            return this.api.getById(id ?: "0")
        } catch (e: Exception) {
            println(e)
            return null
        }
    }

    suspend fun getAllClienteStatus(): List<ClienteDto> {
        try {
            return this.api.getAllStatus()
        } catch (e: Exception) {
            println(e)
            return emptyList()
        }
    }

    suspend fun insertCliente(cliente: ClienteDto): ClienteDto {
        try {
            return this.api.insert(cliente)
        } catch (e: Exception) {
            println(e)
            return cliente
        }
    }

    suspend fun deleteCliente(id: String) : Boolean {
        try {
            val api = api.delete(id)
            return true // debe verificar si se elimino
        } catch (e: Exception) {
            println(e)
            return false
        }
    }

    suspend fun updateCliente(id: String, cliente: ClienteDto): ClienteDto {
        try {
            return this.api.update(id, cliente)
        } catch (e: Exception) {
            println(e)
            return cliente
        }
    }
}