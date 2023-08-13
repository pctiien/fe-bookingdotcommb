package com.example.bookingdotcom.networkService

import com.example.bookingdotcom.model.Location
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("api/Location")
    suspend fun getLocationData(@Query("destination") destination: String): List<Location>
}
