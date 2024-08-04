package com.example.roomchronoapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.roomchronoapp.model.Chronos
import kotlinx.coroutines.flow.Flow

// Interface -> Repository -> ViewModel -> View //
@Dao // Data Access Observer
interface ChronosDatabaseDao {
    // CRUD
    @Query("SELECT * FROM chronos")
    fun getChronos(): Flow<List<Chronos>>

    @Query("SELECT * FROM chronos WHERE id = :id")
    fun getChronosById(id: Long): Flow<Chronos>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(chronos: Chronos)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(chronos: Chronos)

    @Delete
    suspend fun delete(chronos: Chronos)
}








