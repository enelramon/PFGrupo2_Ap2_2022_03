package edu.ucne.appliedbarbershop.data.local.dao

import androidx.room.*
import edu.ucne.appliedbarbershop.data.local.models.Entorno
import edu.ucne.appliedbarbershop.data.local.models.Servicio
import kotlinx.coroutines.flow.Flow

@Dao
interface EntornoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entorno: Entorno)

    @Query("Select * from Entorno where entornoId = 1")
    fun getEntorno(): Flow<Entorno?>
}