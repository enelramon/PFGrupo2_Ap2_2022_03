package edu.ucne.appliedbarbershop.data.local.dao

import androidx.room.*
import edu.ucne.appliedbarbershop.data.local.models.Cita
import edu.ucne.appliedbarbershop.data.local.models.CitaCompleta
import kotlinx.coroutines.flow.Flow

@Dao
interface CitaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cita: Cita)

    @Delete // Cambiar por Query que haga update a Status
    suspend fun delete(cita: Cita)

    @Query(
        "Select " +
        "c.citaId, " +
        "c.servicioId, " +
        "c.barberoId, " +
        "c.clienteId, " +
        "c.fecha, " +
        "c.mensaje, " +
        "c.usuarioCreacionId, " +
        "c.usuarioModificacionId, " +
        "c.status, " +
        "b.Nombre as barberoNombre, " +
        "b.Apellido as barberoApellido, " +
        "b.Celular as barberoCelular, " +
        "b.Imagen as barberoImagen, " +
        "cl.Nombre as clienteNombre, " +
        "cl.Apellido as clienteApellido, " +
        "cl.Celular as clienteCelular, " +
        "cl.Imagen as clienteImagen, " +
        "s.nombre as servicioNombre " +
        "from Citas as c " +
        "left join servicios as s on c.servicioId = s.servicioId " +
        "left join barberos as b on b.barberoId = c.barberoId " +
        "left join clientes as cl on cl.clienteId = c.clienteId "
    )
    fun getAll(): Flow<List<CitaCompleta>>

    @Query(
        "Select " +
        "c.citaId, " +
        "c.servicioId, " +
        "c.barberoId, " +
        "c.clienteId, " +
        "c.fecha, " +
        "c.mensaje, " +
        "c.usuarioCreacionId, " +
        "c.usuarioModificacionId, " +
        "c.status, " +
        "b.Nombre as barberoNombre, " +
        "b.Apellido as barberoApellido, " +
        "b.Celular as barberoCelular, " +
        "b.Imagen as barberoImagen, " +
        "cl.Nombre as clienteNombre, " +
        "cl.Apellido as clienteApellido, " +
        "cl.Celular as clienteCelular, " +
        "cl.Imagen as clienteImagen, " +
        "s.nombre as servicioNombre " +
        "from Citas as c " +
        "left join servicios as s on c.servicioId = s.servicioId " +
        "left join barberos as b on b.barberoId = c.barberoId " +
        "left join clientes as cl on cl.clienteId = c.clienteId " +
        "where citaId=:id"
    )
    suspend fun getById(id: Int): CitaCompleta?
}