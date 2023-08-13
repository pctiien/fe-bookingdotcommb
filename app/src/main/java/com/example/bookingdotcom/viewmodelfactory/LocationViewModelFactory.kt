package com.example.bookingdotcom.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bookingdotcom.repository.LocationRepository
import com.example.bookingdotcom.viewmodel.LocationVM

@Suppress("UNCHECKED_CAST")
class LocationViewModelFactory(private val repository: LocationRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationVM::class.java)) {
            return LocationVM(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
