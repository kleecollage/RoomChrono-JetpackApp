package com.example.roomchronoapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomchronoapp.model.Chronos

@Database(entities = [Chronos::class], version = 1, exportSchema = false)
abstract class ChronosDatabase: RoomDatabase() {
    abstract fun chronosDao(): ChronosDatabaseDao
}