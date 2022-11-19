package edu.ucne.appliedbarbershop.data.remote.api_dao

import edu.ucne.appliedbarbershop.data.remote.dto.ServicioDto
import retrofit2.Response
import retrofit2.http.*

interface ServicioApi {

    @GET("/Servicios/GetServicios")
    suspend fun getAll(): List<ServicioDto>

    @GET("/Servicios/{id}")
    suspend fun getById(@Path("id") id: String): ServicioDto

    @GET("/Servicios/GetServiciosStatus")
    suspend fun getAllStatus(): List<ServicioDto>

    @PUT("/Servicios/PutServicios{id}")
    suspend fun update(@Path("id") id: String, @Body servicio: ServicioDto): ServicioDto

    @POST("/Servicios/PostServicios")
    suspend fun insert(@Body servicio: ServicioDto): ServicioDto

    @DELETE("/Servicios/Delete{id}")
    suspend fun delete(@Path("id") id: String): ServicioDto

}