package com.example.bookingdotcom.model

import com.google.gson.annotations.SerializedName


data class BedType (

    @SerializedName("bedTypeName" ) var bedTypeName : String? = null

): java.io.Serializable