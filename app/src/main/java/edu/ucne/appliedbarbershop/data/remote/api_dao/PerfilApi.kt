package edu.ucne.appliedbarbershop.data.remote.api_dao

import edu.ucne.appliedbarbershop.data.remote.dto.PerfilDto
import retrofit2.Response
import retrofit2.http.*

interface PerfilApi {

    @GET("")
    suspend fun getAll(): Response<List<PerfilDto>>

    @GET("{id}")
    suspend fun getById(@Path("id") id: String): Response<PerfilDto>

    @POST("")
    suspend fun insert(@Body perfil: PerfilDto): Response<PerfilDto>

    @DELETE("{id}")
    suspend fun delete(@Path("id") id: String): Response<PerfilDto>

    @PUT("{id}")
    suspend fun update(@Path("id") id: String, @Body perfil: PerfilDto): Response<PerfilDto>
}