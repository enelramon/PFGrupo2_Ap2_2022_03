package edu.ucne.appliedbarbershop.data.remote.api_dao

import edu.ucne.appliedbarbershop.data.remote.dto.CitaDto
import retrofit2.Response
import retrofit2.http.*

interface CitaApi {

    @GET("")
    suspend fun getAll(): Response<List<CitaDto>>

    @GET("{id}")
    suspend fun getById(@Path("id") id: String): Response<CitaDto>

    @POST("")
    suspend fun insert(@Body cita: CitaDto): Response<CitaDto>

    @DELETE("{id}")
    suspend fun delete(@Path("id") id: String): Response<CitaDto>

    @PUT("{id}")
    suspend fun update(@Path("id") id: String, @Body cita: CitaDto): Response<CitaDto>
}