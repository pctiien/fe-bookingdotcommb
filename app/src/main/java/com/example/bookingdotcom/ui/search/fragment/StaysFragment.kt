package com.example.bookingdotcom.ui.search.fragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.bookingdotcom.model.LocationRequestModel
import com.example.bookingdotcom.model.LocationRequestViewModel
import com.example.bookingdotcom.repository.LocationRepository
import com.example.bookingdotcom.ui.search.fragment.staysChild.LocationActivity
import com.example.bookingdotcom.ui.search_activity.StaysSearchActivity
import com.example.bookingdotcom.viewmodel.LocationVM
import com.example.bookingdotcom.viewmodelfactory.LocationViewModelFactory
import com.example.myapplication.databinding.FragmentStaysBinding
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StaysFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StaysFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    var locationRequestModel = LocationRequestModel()
    private val locationViewModel :LocationRequestViewModel by viewModels()
    private lateinit var locationVM :LocationVM
    private lateinit var roomClientBottomSheet : RoomClientsBottomSheetDialog
    private lateinit var mainBinding : FragmentStaysBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mainBinding = FragmentStaysBinding.inflate(layoutInflater)

        mainBinding.model = locationViewModel

        mainBinding.lifecycleOwner = this

        roomClientBottomSheet = RoomClientsBottomSheetDialog(model = locationViewModel)

        mainBinding.roomClientsContainer.setOnClickListener {
            roomClientBottomSheet.show(this.childFragmentManager,"Room Clients Bottom Sheet Dialog")
        }
        mainBinding.staysSearch.setOnClickListener {
            val intent = Intent(activity,StaysSearchActivity::class.java)
            startActivity(intent)
        }

        mainBinding.searchButton.setOnClickListener {
            val intent = Intent(requireContext(),LocationActivity::class.java)
            intent.putExtra("Location Request Model",locationRequestModel)
            startActivity(intent)
        }

        implementDateRangePicker()

        locationVM = ViewModelProvider(
            this,
            LocationViewModelFactory(LocationRepository())
        )[LocationVM::class.java]

        return mainBinding.root
    }

    private fun implementDateRangePicker() {
        var selectedRange = Pair(ZonedDateTime.of(locationRequestModel.Checkin, ZoneId.systemDefault()).toInstant().toEpochMilli()
                            ,ZonedDateTime.of(locationRequestModel.Checkout, ZoneId.systemDefault()).toInstant().toEpochMilli())
        mainBinding.staysWeek.setOnClickListener {
            val nextYear = Calendar.getInstance()
            nextYear.add(Calendar.YEAR,1)
            nextYear.set(Calendar.MONTH,Calendar.getInstance().get(Calendar.MONTH))
            val constraintsBuilder = CalendarConstraints.Builder()
            constraintsBuilder.setStart(Calendar.getInstance().timeInMillis)
            constraintsBuilder.setEnd(nextYear.timeInMillis)
            val datePickerBuilder = MaterialDatePicker.Builder.dateRangePicker()
            datePickerBuilder.setCalendarConstraints(constraintsBuilder.build())
            datePickerBuilder.setSelection(selectedRange)
            val datePicker = datePickerBuilder.build()
            datePicker.show(childFragmentManager,"Date range picker")
            datePicker.addOnPositiveButtonClickListener {
                selectedRange = it
                locationViewModel.setCheckinout(selectedRange.first,selectedRange.second)
            }
            // Setting up the event for when cancelled is clicked
            datePicker.addOnNegativeButtonClickListener {
            }

            // Setting up the event for when back button is pressed
            datePicker.addOnCancelListener {
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StaysFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StaysFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}