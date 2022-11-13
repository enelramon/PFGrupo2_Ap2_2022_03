package edu.ucne.appliedbarbershop.data.remote.api_dao

import edu.ucne.appliedbarbershop.data.remote.dto.ServicioDto
import retrofit2.Response
import retrofit2.http.*

interface ServicioApi {

    @GET("")
    suspend fun getAll(): Response<List<ServicioDto>>

    @GET("{id}")
    suspend fun getById(@Path("id") id: String): Response<ServicioDto>

    @POST("")
    suspend fun insert(@Body servicio: ServicioDto): Response<ServicioDto>

    @DELETE("{id}")
    suspend fun delete(@Path("id") id: String): Response<ServicioDto>

    @PUT("{id}")
    suspend fun update(@Path("id") id: String, @Body servicio: ServicioDto): Response<ServicioDto>
}