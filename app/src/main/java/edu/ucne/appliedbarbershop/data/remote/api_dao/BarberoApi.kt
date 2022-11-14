package edu.ucne.appliedbarbershop.data.remote.api_dao

import edu.ucne.appliedbarbershop.data.remote.dto.BarberoDto
import retrofit2.Response
import retrofit2.http.*

interface BarberoApi {

    @GET("/api/Barberos")
    suspend fun getAll(): Response<List<BarberoDto>>

    @GET("/api/Barberos/{id}")
    suspend fun getById(@Path("id") id: String): Response<BarberoDto>

    @POST("/api/Barberos")
    suspend fun insert(@Body barbero: BarberoDto): Response<BarberoDto>

    @DELETE("/api/Barberos/{id}")
    suspend fun delete(@Path("id") id: String): Response<BarberoDto>

    @PUT("/api/Barberos/{id}")
    suspend fun update(@Path("id") id: String, @Body barbero: BarberoDto): Response<BarberoDto>

}