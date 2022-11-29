package edu.ucne.appliedbarbershop.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import edu.ucne.appliedbarbershop.data.local.models.Barbero
import edu.ucne.appliedbarbershop.data.local.models.Cita
import kotlinx.coroutines.flow.Flow

@Dao
interface BarberoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(barbero: Barbero)

    @Delete // Cambiar por Query que haga update a Status
    suspend fun delete(barbero: Barbero)

    @Query("Select * from Barberos") // Añadir condicion que solo traiga los del status 1
    fun getAll(): Flow<List<Barbero>>

    @Query("Select * from Barberos where barberoId=:id")
    fun getById(id:Int): Flow<Barbero?>

    @Query("Delete from barberos")
    suspend fun truncateTable()
}