package com.example.bookingdotcom.model

import com.google.gson.annotations.SerializedName


data class Location (

   @SerializedName("locationId"     ) var locationId     : Int?    = null,
   @SerializedName("locationName"   ) var locationName   : String? = null,
   @SerializedName("country"        ) var country        : String? = null,
   @SerializedName("address"        ) var address        : String? = null,
   @SerializedName("city"           ) var city           : String? = null,
   @SerializedName("description"    ) var description    : String? = null,
   @SerializedName("poster"         ) var poster         : String? = null,
   @SerializedName("rating"         ) var rating         : Double? = null,
   @SerializedName("discount"       ) var discount       : Int?    = null,
   @SerializedName("ratingQuantity" ) var ratingQuantity : Int?    = null,
   @SerializedName("room"           ) var room           : Room?   = Room()

) : java.io.Serializable
