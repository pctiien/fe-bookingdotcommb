package com.example.bookingdotcom.ui.search.fragment.staysChild

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingdotcom.model.Location
import com.example.bookingdotcom.model.Rating
import com.example.bookingdotcom.networkService.ApiState
import com.example.bookingdotcom.repository.LocationRepository
import com.example.bookingdotcom.viewmodel.LocationVM
import com.example.bookingdotcom.viewmodelfactory.LocationViewModelFactory
import com.example.myapplication.R
import kotlinx.coroutines.launch
import java.io.Serializable

class LocationDetailActivity : AppCompatActivity() {
    lateinit var location : Location
    lateinit var locationName : TextView
    lateinit var ratingScore : TextView
    lateinit var price : TextView
    lateinit var discount : TextView
    lateinit var locationVM : LocationVM
    lateinit var LocationImgRecycler : RecyclerView
    lateinit var LocationImgAdapter : LocationImgAdapter
    lateinit var LocationRatingRecycler : RecyclerView
    lateinit var LocationRatingAdapter : LocationRatingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_detail)
        location = intent.getSerializable("Location",Location::class.java)
        LocationRatingRecycler = findViewById(R.id.list_ratingScores)
        LocationImgRecycler = findViewById(R.id.list_locationImg)
        locationName = findViewById(R.id.txt_locationName)
        ratingScore = findViewById(R.id.txt_scores)
        price = findViewById(R.id.txt_price)
        discount = findViewById(R.id.txt_discount)
        locationVM = ViewModelProvider(
            this,
            LocationViewModelFactory(LocationRepository())
        )[LocationVM::class.java]
            locationName.text = location.locationName
            ratingScore.text = location.rating.toString()
            if(location.discount!!>0)
            {
                price.text = (location.room!!.price!!*(100-location.discount!!)/100).toString()
                discount.text = location.room!!.price.toString()
            }else{
                discount.visibility = View.GONE
                price.text = location.room!!.price.toString()
            }
            locationVM.getLocationImgs(location.locationId!!)
            locationVM.getLocationRatings(location.locationId!!)
            lifecycleScope.launch {
                locationVM.myDataList.collect{apiState->
                    when(apiState)
                    {
                        is ApiState.Success->{
                            val data = apiState.data
                            if(data is List<*>)
                            {
                                when(apiState.source)
                                {
                                    "getLocationImgs"->{
                                        val dataList: MutableList<String>
                                        dataList = data as MutableList<String>
                                        LocationImgAdapter = LocationImgAdapter(dataList)
                                        LocationImgRecycler.adapter = LocationImgAdapter
                                    }
                                    "getLocationRatings"->{
                                        val dataList : MutableList<Rating>
                                        dataList = data as MutableList<Rating>
                                        LocationRatingAdapter = LocationRatingAdapter(dataList)
                                        LocationRatingRecycler.adapter = LocationRatingAdapter
                                    }
                                }

                            }
                        }
                        is ApiState.Loading->{
                            Log.d("api_connect","location imgs loading")
                        }
                        is ApiState.Failure->{
                            Log.d("api_connect","location imgs failure")

                        }
                        is ApiState.Empty->{

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