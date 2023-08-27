package com.example.bookingdotcom.model

import com.google.gson.annotations.SerializedName


data class Room (

    @SerializedName("roomId"   ) var roomId   : Int?      = null,
    @SerializedName("roomSize" ) var roomSize : Int?      = null,
    @SerializedName("price"    ) var price    : Int?      = null,
    @SerializedName("roomType" ) var roomType : RoomType? = RoomType()

): java.io.Serializable