package edu.ucne.appliedbarbershop.data.remote.api_dao

import edu.ucne.appliedbarbershop.data.remote.dto.BarberoDto
import retrofit2.Response
import retrofit2.http.*

interface BarberoApi {

    @GET("")
    suspend fun getAll(): Response<List<BarberoDto>>

    @GET("{id}")
    suspend fun getById(@Path("id") id: String): Response<BarberoDto>

    @POST("")
    suspend fun insert(@Body barbero: BarberoDto): Response<BarberoDto>

    @DELETE("{id}")
    suspend fun delete(@Path("id") id: String): Response<BarberoDto>

    @PUT("{id}")
    suspend fun update(@Path("id") id: String, @Body barbero: BarberoDto): Response<BarberoDto>

}