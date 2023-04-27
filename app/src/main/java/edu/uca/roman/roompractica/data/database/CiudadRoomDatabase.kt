package edu.uca.roman.roompractica.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import edu.uca.roman.roompractica.data.database.dao.CiudadDao
import edu.uca.roman.roompractica.data.database.entities.Ciudad

@Database(entities = [Ciudad::class], version = 1, exportSchema = false)
abstract class CiudadRoomDatabase: RoomDatabase() {

    abstract fun CiudadDao(): CiudadDao

    companion object {
        @Volatile
        private var INSTANCE: CiudadRoomDatabase? = null

        fun getDatabase(context: Context): CiudadRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CiudadRoomDatabase::class.java,
                    "ciudad_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}