package edu.ucne.appliedbarbershop.data.local.dao

import androidx.room.*
import edu.ucne.appliedbarbershop.data.local.models.Cita
import edu.ucne.appliedbarbershop.data.local.models.Cliente
import kotlinx.coroutines.flow.Flow

@Dao
interface ClienteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cliente: Cliente)

    @Delete // Cambiar por Query que haga update a Status
    suspend fun delete(cliente: Cliente)

    @Query("Select * from Clientes") // Añadir condicion que solo traiga los del status 1
    fun getAll(): Flow<List<Cliente>>

    @Query("Select * from Clientes where clienteId=:id")
    fun getById(id:Int): Flow<Cliente?>

    @Query("Delete from clientes")
    suspend fun truncateTable()
}