package edu.uca.roman.roompractica.ui.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import edu.uca.roman.roompractica.data.database.dao.CiudadDao
import edu.uca.roman.roompractica.data.database.entities.Ciudad

class CiudadViewModel(private val ciudadDao: CiudadDao): ViewModel() {

    val allCiudad: LiveData<List<Ciudad>> = ciudadDao.getCiudad().asLiveData()

    fun agregarCiudad(ciudad: String) {
        val nuevaCiudad = Ciudad(
            nombre = ciudad
        )
        insertCiudad(nuevaCiudad)
    }

    private fun insertCiudad(ciudad: Ciudad) {
        viewModelScope.launch {
            ciudadDao.insert(ciudad)
        }
    }

    fun entradasValidas(ciudad: String): Boolean {
        if (ciudad.isBlank()) {
            return false
        }
        return true
    }
}

class CiudadViewModelFactory(private val ciudadDao: CiudadDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CiudadViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CiudadViewModel(ciudadDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}