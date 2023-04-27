package edu.uca.roman.roompractica.data.database.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import edu.uca.roman.roompractica.data.database.entities.Ciudad

@Dao
interface CiudadDao {

    @Query("SELECT * from word ORDER BY word ASC")
    fun getCiudad(): Flow<List<Ciudad>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ciudad: Ciudad)

    @Update
    suspend fun update(ciudad: Ciudad)

    @Delete
    suspend fun delete(ciudad: Ciudad)
}