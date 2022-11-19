package edu.ucne.appliedbarbershop.data.remote.api_dao

import edu.ucne.appliedbarbershop.data.remote.dto.PerfilDto
import retrofit2.Response
import retrofit2.http.*

interface PerfilApi {

    @GET("/Clientes/GetClientes")
    suspend fun getAll(): List<PerfilDto>

    @GET("/Clientes/GetClientesStatus")
    suspend fun getAllStatus(): List<PerfilDto>

    @GET("/Clientes/GetClientes{id}")
    suspend fun getById(@Path("id") id: String): PerfilDto

    @PUT("/Clientes/PutClientes{id}")
    suspend fun update(@Path("id") id: String, @Body perfil: PerfilDto): PerfilDto

    @POST("/Clientes/PostClientes")
    suspend fun insert(@Body perfil: PerfilDto): PerfilDto

    @DELETE("/Clientes/Delete{id}")
    suspend fun delete(@Path("id") id: String): PerfilDto

}