package com.example.bookingdotcom.repository
import com.example.bookingdotcom.model.Location
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.Dispatchers
import com.example.bookingdotcom.networkService.RetrofitClient
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LocationRepository {
    fun getListLocation(destination:String): Flow<List<Location>> = flow {
        val r = RetrofitClient.retrofit.getLocationData(destination)
        emit(r)
    }.flowOn(Dispatchers.IO)
}