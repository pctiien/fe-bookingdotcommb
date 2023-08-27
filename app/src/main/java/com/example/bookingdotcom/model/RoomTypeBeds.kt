package com.example.bookingdotcom.model

import com.google.gson.annotations.SerializedName


data class RoomTypeBeds (

    @SerializedName("quantity" ) var quantity : Int?     = null,
    @SerializedName("bedType"  ) var bedType  : BedType? = BedType()

): java.io.Serializable