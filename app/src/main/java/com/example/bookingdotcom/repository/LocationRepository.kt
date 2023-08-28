package com.example.bookingdotcom.repository
import androidx.annotation.RestrictTo
import com.example.bookingdotcom.model.Location
import com.example.bookingdotcom.model.LocationRequestModel
import com.example.bookingdotcom.model.Rating
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.Dispatchers
import com.example.bookingdotcom.networkService.RetrofitClient
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LocationRepository {
    fun getListLocation(model:LocationRequestModel): Flow<List<Location>> = flow {
        val r = RetrofitClient.retrofit.getLocationData(model)
        emit(r)
    }.flowOn(Dispatchers.IO)
    fun getLocationImgs(location_id : Int):Flow<List<String>> = flow{
        val r = RetrofitClient.retrofit.getLocationImgs(location_id)
        emit(r)
    }
    fun getLocationRatings(locationId : Int):Flow<List<Rating>> = flow{
        val r = RetrofitClient.retrofit.getLocationRatings(locationId)
        emit(r)
    }
}