package com.example.bookingdotcom.ui.search.fragment.staysChild

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatImageView
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
import java.io.Serializable

class LocationActivity : AppCompatActivity() {
    lateinit var locationRV: RecyclerView
    lateinit var locationRVAdapter: LocationAdapter
    lateinit var locationVM: LocationVM
    lateinit var locationDataList: MutableList<Location>
    lateinit var backIcon: AppCompatImageView
    var locationRequestModel = LocationRequestModel()
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        backIcon = findViewById(R.id.back_icon)
        locationRV = findViewById(R.id.locationRV)
        locationRequestModel =
            intent.getParcelableExtra("Location Request Model")!!
        Log.d("Location Request Model", locationRequestModel.Checkin.toString())
        locationDataList = mutableListOf<Location>()
        locationVM = ViewModelProvider(
            this,
            LocationViewModelFactory(LocationRepository())
        )[LocationVM::class.java]
        locationVM.getListLocation(LocationRequestModel())
        backIcon.setOnClickListener {
            this.finish()
        }
        lifecycleScope.launch {
            locationVM.myDataList.collect { apiState ->
                when (apiState) {
                    is ApiState.Success -> {
                        var result = apiState.data
                        if (result is ArrayList<*>) {
                            locationDataList = result as MutableList<Location>
                            locationRVAdapter = LocationAdapter(locationDataList)
                            locationRV.adapter = locationRVAdapter
                        }
                    }
                    is ApiState.Failure -> {
                        Log.d("api_connect", "location controller failure")
                    }
                    is ApiState.Loading -> {
                        Log.d("api_connect", "location controller loading")

                    }
                    is ApiState.Empty -> {
                        Log.d("api_connect", "location controller empty")
                    }

                }

            }
        }
    }

    fun <T : Serializable?> Intent.getSerializable(key: String, m_class: Class<T>): T {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            this.getSerializableExtra(key, m_class)!!
        else
            this.getSerializableExtra(key) as T

    }
}