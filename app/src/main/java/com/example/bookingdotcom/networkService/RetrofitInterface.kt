package com.example.bookingdotcom.networkService

import com.example.bookingdotcom.model.Location
import com.example.bookingdotcom.model.LocationRequestModel
import com.example.bookingdotcom.model.Rating
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("api/Location")
    suspend fun getLocationData(@Query("model") model : LocationRequestModel): List<Location>
    @GET("api/Location/Images")
    suspend fun getLocationImgs(@Query("location_id") location_id : Int) : List<String>
    @GET("api/Location/Ratings")
    suspend fun getLocationRatings(@Query("locationId") locationId : Int) : List<Rating>
}
