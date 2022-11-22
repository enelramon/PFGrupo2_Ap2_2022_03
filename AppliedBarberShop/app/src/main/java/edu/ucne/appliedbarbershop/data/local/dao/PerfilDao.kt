package edu.ucne.appliedbarbershop.data.local.dao

import androidx.room.*
import edu.ucne.appliedbarbershop.data.local.models.Perfil
import kotlinx.coroutines.flow.Flow

@Dao
interface PerfilDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(perfil: Perfil)

    @Delete // Cambiar por Query que haga update a Status
    suspend fun delete(perfil: Perfil)

    @Query("Select * from Perfiles") // Añadir condicion que solo traiga los del status 1
    fun getAll(): Flow<List<Perfil>>

    @Query("Select * from Perfiles where perfilId=:id")
    suspend fun getById(id:Int): Perfil?
}