package edu.ucne.appliedbarbershop.data.remote.api_dao

import edu.ucne.appliedbarbershop.data.remote.dto.CitaDto
import retrofit2.Response
import retrofit2.http.*

interface CitaApi {

    @GET("/Citas/GetCitas")
    suspend fun getAll(): List<CitaDto>

    @GET("/Citas/GetCitas{id}")
    suspend fun getById(@Path("id") id: String): CitaDto

    @GET("/Citas/GetClientesId{id}")
    suspend fun getAllByClienteId(@Path("id") id: String): List<CitaDto>

    @PUT("Citas/PutCitas{id}")
    suspend fun update(@Path("id") id: String, @Body cita: CitaDto): CitaDto

    @POST("/Citas/PostCitas")
    suspend fun insert(@Body cita: CitaDto): CitaDto

    @DELETE("/Citas/Delete{id}")
    suspend fun delete(@Path("id") id: String): CitaDto

}