package com.example.bookingdotcom.ui.search.fragment.staysChild

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingdotcom.model.Location
import com.example.bookingdotcom.model.LocationRequestModel
import com.example.bookingdotcom.networkService.ApiState
import com.example.bookingdotcom.repository.LocationRepository
import com.example.bookingdotcom.viewmodel.LocationVM
import com.example.bookingdotcom.viewmodelfactory.LocationViewModelFactory
import com.example.myapplication.R
import kotlinx.coroutines.launch

class LocationActivity : AppCompatActivity() {
    lateinit var locationRV : RecyclerView
    lateinit var locationRVAdapter : LocationAdapter
    lateinit var locationVM :LocationVM
    lateinit var locationDataList : MutableList<Location>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        locationRV = findViewById(R.id.locationRV)
        locationDataList = mutableListOf<Location>()
        locationVM = ViewModelProvider(
            this,
            LocationViewModelFactory(LocationRepository())
        )[LocationVM::class.java]
        locationVM.getListLocation(LocationRequestModel())
        lifecycleScope.launch{
            locationVM.myDataList.collect{ apiState->
                when(apiState)
                {
                    is ApiState.Success -> {
                        var result = apiState.data
                        if(result is ArrayList<*>)
                        {
                            locationDataList = result as MutableList<Location>
                            locationRVAdapter = LocationAdapter(locationDataList)
                            locationRV.adapter = locationRVAdapter
                        }
                    }
                    is ApiState.Failure ->{
                        Log.d("api_connect","location controller failure")
                    }
                    is ApiState.Loading -> {
                        Log.d("api_connect","location controller loading")

                    }
                    is ApiState.Empty ->{
                        Log.d("api_connect","location controller empty")
                    }

                }

            }
        }
    }
}