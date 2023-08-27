package com.example.bookingdotcom.networkService

sealed class ApiState {
    object Loading : ApiState()
    class Failure(val e: Throwable) : ApiState()
    class Success(val data: Any,val source : String) : ApiState()
    object Empty : ApiState()
}