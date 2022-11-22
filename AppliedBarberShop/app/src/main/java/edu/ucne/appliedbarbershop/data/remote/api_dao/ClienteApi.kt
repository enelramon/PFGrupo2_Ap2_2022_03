package edu.ucne.appliedbarbershop.data.remote.api_dao

import edu.ucne.appliedbarbershop.data.remote.dto.ClienteDto
import retrofit2.http.*

interface ClienteApi {

    @GET("/Clientes/GetClientes")
    suspend fun getAll(): List<ClienteDto>

    @GET("/Clientes/GetClientesStatus")
    suspend fun getAllStatus(): List<ClienteDto>

    @GET("/Clientes/GetClientes{id}")
    suspend fun getById(@Path("id") id: String): ClienteDto

    @PUT("/Clientes/PutClientes{id}")
    suspend fun update(@Path("id") id: String, @Body cliente: ClienteDto): ClienteDto

    @POST("/Clientes/PostClientes")
    suspend fun insert(@Body cliente: ClienteDto): ClienteDto

    @DELETE("/Clientes/Delete{id}")
    suspend fun delete(@Path("id") id: String): ClienteDto

}