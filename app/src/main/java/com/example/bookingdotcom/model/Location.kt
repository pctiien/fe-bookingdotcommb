package com.example.bookingdotcom.model

import com.google.gson.annotations.SerializedName

 data class Location (

    @SerializedName("locationId")
    var locationId   : Int?    = null,
    @SerializedName("locationName")
    var locationName : String? = null,
    @SerializedName("country")
    var country      : String? = null,
    @SerializedName("address")
    var address      : String? = null,
    @SerializedName("city")
    var city         : String? = null,
    @SerializedName("description")
    var description  : String? = null,
    @SerializedName("poster")
    var poster       : String? = null,
) : java.io.Serializable
