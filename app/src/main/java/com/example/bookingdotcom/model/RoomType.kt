package com.example.bookingdotcom.model

import com.google.gson.annotations.SerializedName


data class RoomType (

    @SerializedName("roomTypeId"   ) var roomTypeId   : Int?                    = null,
    @SerializedName("maxOccupancy" ) var maxOccupancy : Int?                    = null,
    @SerializedName("roomTypeName" ) var roomTypeName : RoomTypeName?           = RoomTypeName(),
    @SerializedName("roomTypeBeds" ) var roomTypeBeds : ArrayList<RoomTypeBeds> = arrayListOf()

)