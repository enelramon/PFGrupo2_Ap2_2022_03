package edu.ucne.appliedbarbershop.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import edu.ucne.appliedbarbershop.data.local.models.Servicio
import kotlinx.coroutines.flow.Flow

@Dao
interface ServicioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(servicio: Servicio)

    @Delete // Cambiar por Query que haga update a Status
    suspend fun delete(servicio: Servicio)

    @Query("Select * from Servicios") // Añadir condicion que solo traiga los del status 1
    fun getAll(): Flow<List<Servicio>>

    @Query("Select * from Servicios where servicioId=:id")
    suspend fun getById(id:Int): Servicio?
}