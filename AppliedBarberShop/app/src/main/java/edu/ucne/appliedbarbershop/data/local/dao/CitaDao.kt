package edu.ucne.appliedbarbershop.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import edu.ucne.appliedbarbershop.data.local.models.Cita
import kotlinx.coroutines.flow.Flow

@Dao
interface CitaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cita: Cita)

    @Delete // Cambiar por Query que haga update a Status
    suspend fun delete(cita: Cita)

    @Query("Select * from Citas") // Añadir condicion que solo traiga los del status 1
    fun getAll(): Flow<List<Cita>>

    @Query("Select * from Citas where citaId=:id")
    suspend fun getById(id:Int): Cita?
}