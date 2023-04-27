package edu.uca.roman.roompractica.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity
data class Ciudad(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    @ColumnInfo(name = "ciudad")
    val nombre: String,
    //val anioFun: Int
)
