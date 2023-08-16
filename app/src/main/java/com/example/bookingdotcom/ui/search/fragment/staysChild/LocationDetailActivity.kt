package com.example.bookingdotcom.ui.search.fragment.staysChild

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.bookingdotcom.model.Location
import com.example.myapplication.R
import java.io.Serializable

class LocationDetailActivity : AppCompatActivity() {
    lateinit var location : Location
    lateinit var locationName : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_detail)
        location = intent.getSerializable("Location",Location::class.java)
        locationName = findViewById(R.id.txt_locationName)
        if(location!=null)
        {
            locationName.text = location.locationName
        }
    }

    fun <T : Serializable?> Intent.getSerializable(key: String, m_class: Class<T>): T {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            this.getSerializableExtra(key, m_class)!!
        else
            this.getSerializableExtra(key) as T
    }
}