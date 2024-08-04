package com.example.roomchronoapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Entidades = Tabla  | Atributo = Campo
@Entity(tableName = "chronos")
data class Chronos(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "chronos")
    val chronos: Long
)