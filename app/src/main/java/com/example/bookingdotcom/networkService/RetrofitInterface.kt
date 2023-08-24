package com.example.bookingdotcom.networkService

import com.example.bookingdotcom.model.Location
import com.example.bookingdotcom.model.LocationRequestModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("api/Location")
    suspend fun getLocationData(@Query("model") model : LocationRequestModel): List<Location>
}
