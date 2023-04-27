package edu.uca.roman.roompractica

import android.app.Application
import edu.uca.roman.roompractica.data.database.CiudadRoomDatabase

class CiudadApplication: Application() {

    val database: CiudadRoomDatabase by lazy {CiudadRoomDatabase.getDatabase(this)}
}