package com.example.roomchronoapp.repository

import com.example.roomchronoapp.model.Chronos
import com.example.roomchronoapp.room.ChronosDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ChronosRepository @Inject constructor(private val chronosDatabaseDao: ChronosDatabaseDao) {
    suspend fun addChrono(chrono: Chronos) = chronosDatabaseDao.insert(chrono)
    suspend fun updateChrono(chrono: Chronos) = chronosDatabaseDao.update(chrono)
    suspend fun deleteChrono(chrono: Chronos) = chronosDatabaseDao.delete(chrono)
    fun getAllChronos(): Flow<List<Chronos>> = chronosDatabaseDao.getChronos().flowOn(Dispatchers.IO).conflate()
    fun getChronoById(id: Long): Flow<Chronos> = chronosDatabaseDao.getChronosById(id).flowOn(Dispatchers.IO).conflate()
}