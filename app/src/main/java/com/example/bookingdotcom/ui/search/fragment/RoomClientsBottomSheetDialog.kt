package com.example.bookingdotcom.ui.search.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import com.example.bookingdotcom.model.LocationRequestModel
import com.example.bookingdotcom.model.LocationRequestViewModel
import com.example.myapplication.R
import com.example.myapplication.databinding.BottomsheetRoomclientBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

public class RoomClientsBottomSheetDialog(val model : LocationRequestViewModel) : BottomSheetDialogFragment() {
    private lateinit var binding : BottomsheetRoomclientBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomsheetRoomclientBinding.inflate(layoutInflater)
        binding.model = model
        binding.lifecycleOwner = this
        binding.btnAdultMinus.setOnClickListener {
            model.adultReduce()
        }
        binding.btnAdultPlus.setOnClickListener {
            model.adultIncrease()
        }
        binding.btnRoomminus.setOnClickListener {
            model.roomReduce()
        }
        binding.btnRoomPlus.setOnClickListener {
            model.roomIncrease()
        }
        binding.btnChildMinus.setOnClickListener {
            model.childrenReduce()
        }
        binding.btnChildPlus.setOnClickListener {
            model.childrenIncrease()
        }
        return binding.root
    }
}