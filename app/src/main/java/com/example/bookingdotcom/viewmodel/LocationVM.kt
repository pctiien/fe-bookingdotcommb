package com.example.bookingdotcom.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookingdotcom.networkService.ApiState
import com.example.bookingdotcom.repository.LocationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class LocationVM(private var repository: LocationRepository) : ViewModel() {

    val myDataList: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)

    fun getListLocation(destination:String) = viewModelScope.launch {
        myDataList.value = ApiState.Loading
        repository.getListLocation(destination)
            .catch { e ->
                myDataList.value = ApiState.Failure(e)
            }.collect { data ->
                myDataList.value = ApiState.Success(data)
            }
    }

}