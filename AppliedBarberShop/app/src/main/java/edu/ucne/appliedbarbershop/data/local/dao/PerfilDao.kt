package edu.ucne.appliedbarbershop.data.local.dao

import androidx.room.*
import edu.ucne.appliedbarbershop.data.local.models.Entorno
import edu.ucne.appliedbarbershop.data.local.models.Perfil
import edu.ucne.appliedbarbershop.data.local.models.PerfilCompleto
import edu.ucne.appliedbarbershop.data.local.models.Servicio
import kotlinx.coroutines.flow.Flow

@Dao
interface PerfilDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(perfil: Perfil)

    @Delete
    suspend fun delete(perfil: Perfil)

    @Query(
        "Select * from Perfiles as p " +
        "left join Clientes as c on c.clienteId = p.perfilId "
    )
    fun getAll(): Flow<List<PerfilCompleto>>

    @Query(
        "Select * from Perfiles as p " +
        "left join Clientes as c on c.clienteId = p.perfilId " +
        "where perfilId = :id "
    )
    fun getById(id: Int): Flow<PerfilCompleto?>
}