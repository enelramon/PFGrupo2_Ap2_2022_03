package edu.ucne.appliedbarbershop.data.remote.api_dao

import edu.ucne.appliedbarbershop.data.remote.dto.ClienteDto
import retrofit2.Response
import retrofit2.http.*

interface ClienteApi {

    @GET("/Clientes/GetClientes")
    suspend fun getAll(): Response<List<ClienteDto>>

    @GET("/Clientes/GetAllClienteStatus")
    suspend fun getAllStatus(): Response<List<ClienteDto>>

    @GET("/Clientes/GetClientes{id}")
    suspend fun getById(@Path("id") id: String): Response<ClienteDto>

    @POST("/Clientes/PostClientes")
    suspend fun insert(@Body cliente: ClienteDto): Response<ClienteDto>

    @PUT("/Clientes/PutClientes{id}")
    suspend fun update(@Path("id") id: String, @Body cliente: ClienteDto): Response<ClienteDto>

    @DELETE("/Clientes/Delete{id}")
    suspend fun delete(@Path("id") id: String): Response<ClienteDto>
}