package com.example.bookingdotcom.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

class LocationRequestViewModel :ViewModel() {
    private val _locationLiveData :MutableLiveData<LocationRequestModel> = MutableLiveData(
        LocationRequestModel()
    )
    var locationLiveData :LiveData<LocationRequestModel> = _locationLiveData
    fun getDateRange():String{
        return locationLiveData.value!!.getDateRange()
    }
    private val value get() = locationLiveData.value!!
    fun setCheckinout(checkin:Long,checkout : Long)
    {
        _locationLiveData.postValue(this.value.setCheckinout(checkin,checkout))
    }
    fun roomIncrease() {
        _locationLiveData.postValue(this.value.roomIncrease())
    }
    fun adultIncrease()
    {
        _locationLiveData.postValue(this.value.adultIncrease())
    }
    fun childrenIncrease()
    {
        _locationLiveData.postValue(this.value.childrenIncrease())
    }
    fun roomReduce()
    {
        _locationLiveData.postValue(this.value.roomReduce())
    }
    fun adultReduce()
    {
        _locationLiveData.postValue(this.value.adultReduce())
    }
    fun childrenReduce()
    {
        _locationLiveData.postValue(this.value.childrenReduce())
    }


}