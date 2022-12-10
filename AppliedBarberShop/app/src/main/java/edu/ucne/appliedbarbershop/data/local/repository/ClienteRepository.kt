package edu.ucne.appliedbarbershop.data.local.repository

import edu.ucne.appliedbarbershop.data.local.AppDataBase
import edu.ucne.appliedbarbershop.data.local.models.Cliente
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ClienteRepository @Inject constructor(
    private val db: AppDataBase
) {

    suspend fun insert(cliente: Cliente){
        return db.clienteDao.insert(cliente)
    }

    suspend fun delete(cliente: Cliente){
        db.clienteDao.delete(cliente)
    }

    suspend fun getAll(): Flow<List<Cliente>> {
        return db.clienteDao.getAll()
    }

    suspend fun getById(id: Int): Flow<Cliente?> {
        return db.clienteDao.getById(id)
    }

    suspend fun truncateTable() {
        return db.barberoDao.truncateTable()
    }
}