package edu.ucne.appliedbarbershop.data.remote.api_dao

import edu.ucne.appliedbarbershop.data.remote.dto.BarberoDto
import retrofit2.http.*

interface BarberoApi {

    @GET("/Barberos/GetBarberos")
    suspend fun getAll(): List<BarberoDto>

    @GET("/Barberos/GetBarberos{id}")
    suspend fun getById(@Path("id") id: String): BarberoDto

    @GET("/Barberos/GetAllBarberoStatus")
    suspend fun getAllStatus(): List<BarberoDto>

    @PUT("/Barberos/PutBarberos{id}")
    suspend fun update(@Path("id") id: String, @Body barbero: BarberoDto): BarberoDto

    @POST("/Barberos/PostBarberos")
    suspend fun insert(@Body barbero: BarberoDto): BarberoDto

    @DELETE("/Barberos/Delete{id}")
    suspend fun delete(@Path("id") id: String): BarberoDto

}