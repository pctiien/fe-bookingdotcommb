package com.example.bookingdotcom.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime
@Parcelize
class Children(
    var Age : Int,
    var ChildrenQuantity : Int
) : Parcelable {
    constructor():this(17,0);
}
@Parcelize
class LocationRequestModel (
    var Destination : String ="",
    var Checkin: LocalDateTime =LocalDateTime.now(),
    var Checkout : LocalDateTime = LocalDateTime.now().plusDays(1),
    var RoomQuantity : Int=1,
    var AdultQuantity : Int=1,
    var Children : Children=Children()
):Parcelable
