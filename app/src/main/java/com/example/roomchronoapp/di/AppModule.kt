package com.example.roomchronoapp.di

import android.content.Context
import androidx.room.Room
import com.example.roomchronoapp.room.ChronosDatabase
import com.example.roomchronoapp.room.ChronosDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesChronosDao(chronosDatabase: ChronosDatabase): ChronosDatabaseDao {
        return chronosDatabase.chronosDao()
    }

    @Singleton
    @Provides
    fun providesChronosDatabase(@ApplicationContext context: Context): ChronosDatabase {
        return Room.databaseBuilder(
            context,
            ChronosDatabase::class.java,
            "chronos_db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}





















