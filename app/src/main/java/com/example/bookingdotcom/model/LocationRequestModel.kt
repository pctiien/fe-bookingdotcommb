package com.example.bookingdotcom.model

import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.ViewModel
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

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
    var AdultQuantity : Int=2,
    var Children : Children=Children()
):Parcelable
{
    fun setCheckinout(checkin:Long,checkout:Long) : LocationRequestModel
    {
        this.Checkin = LocalDateTime.ofInstant(Instant.ofEpochMilli(checkin), TimeZone.getDefault().toZoneId())
        this.Checkout = LocalDateTime.ofInstant(Instant.ofEpochMilli(checkout), TimeZone.getDefault().toZoneId())
        return this
    }

    fun getDateRange():String{
        val dateFormat = DateTimeFormatter.ofPattern("" +
                "E, dd 'thg' MM", Locale("vi", "VN"))
        return "${dateFormat.format(Checkin)} - ${dateFormat.format(Checkout)}"
    }
    fun roomIncrease():LocationRequestModel
    {
        RoomQuantity++
        return this
    }
    fun adultIncrease() : LocationRequestModel
    {
        AdultQuantity++
        return this
    }
    fun childrenIncrease() : LocationRequestModel
    {
        Children.ChildrenQuantity++
        return this
    }
    fun roomReduce() : LocationRequestModel
    {
        if(RoomQuantity<=0) return this
        RoomQuantity--
        return this
    }
    fun adultReduce() : LocationRequestModel
    {
        if(AdultQuantity<=0) return this
        AdultQuantity--
        return this
    }
    fun childrenReduce(): LocationRequestModel
    {
        if(Children.ChildrenQuantity<=0) return this
        Children.ChildrenQuantity--
        return this
    }



}
