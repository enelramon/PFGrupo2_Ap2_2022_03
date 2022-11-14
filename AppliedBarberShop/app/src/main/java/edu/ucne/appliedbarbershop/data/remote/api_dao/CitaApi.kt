package edu.ucne.appliedbarbershop.data.remote.api_dao

import edu.ucne.appliedbarbershop.data.remote.dto.CitaDto
import retrofit2.Response
import retrofit2.http.*

interface CitaApi {

    @GET("/api/Citas")
    suspend fun getAll(): Response<List<CitaDto>>

    @GET("/api/Citas/{id}")
    suspend fun getById(@Path("id") id: String): Response<CitaDto>

    @POST("/api/Citas")
    suspend fun insert(@Body cita: CitaDto): Response<CitaDto>

    @DELETE("/api/Citas/{id}")
    suspend fun delete(@Path("id") id: String): Response<CitaDto>

    @PUT("/api/Citas/{id}")
    suspend fun update(@Path("id") id: String, @Body cita: CitaDto): Response<CitaDto>
}