package com.example.bookingdotcom.model

import java.time.LocalDateTime

class Children(
    var Age : Int,
    var ChildrenQuantity : Int
) {
    constructor():this(17,0);
}
class LocationRequestModel(
    var Destination : String ="",
    var Checkin: LocalDateTime =LocalDateTime.now(),
    var Checkout : LocalDateTime = LocalDateTime.now().plusDays(1),
    var RoomQuantity : Int=1,
    var AdultQuantity : Int=1,
    var Children : Children=Children()
)
