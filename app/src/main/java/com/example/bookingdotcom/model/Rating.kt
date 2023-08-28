package com.example.bookingdotcom.model
import com.google.gson.annotations.SerializedName


data class Rating (

    @SerializedName("ratingId"    ) var ratingId    : Int?    = null,
    @SerializedName("comment"     ) var comment     : String? = null,
    @SerializedName("userName"    ) var userName    : String? = null,
    @SerializedName("userCountry" ) var userCountry : String? = null,
    @SerializedName("userAvatar"  ) var userAvatar  : String? = null

):java.io.Serializable