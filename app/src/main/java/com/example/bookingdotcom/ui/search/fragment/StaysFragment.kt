package com.example.bookingdotcom.ui.search.fragment
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.util.Pair
import androidx.lifecycle.ViewModelProvider
import com.example.bookingdotcom.model.LocationRequestModel
import com.example.bookingdotcom.repository.LocationRepository
import com.example.bookingdotcom.ui.search.fragment.staysChild.LocationActivity
import com.example.bookingdotcom.ui.search_activity.StaysSearchActivity
import com.example.bookingdotcom.viewmodel.LocationVM
import com.example.bookingdotcom.viewmodelfactory.LocationViewModelFactory
import com.example.myapplication.R
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import kotlin.time.Duration.Companion.days

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
    lateinit var searchContainer : RelativeLayout
    lateinit var calendarContainer :RelativeLayout
    lateinit var roomClientsContainer : RelativeLayout
    private lateinit var locationVM :LocationVM
    lateinit var searchButton : AppCompatButton
    lateinit var roomClientBottomSheet : RoomClientsBottomSheetDialog
    var locationRequestModel = LocationRequestModel()
    private lateinit var txtDateRange : AppCompatTextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_stays, container, false)
        roomClientBottomSheet = RoomClientsBottomSheetDialog()
        roomClientsContainer = view.findViewById(R.id.room_clients_container)
        searchContainer = view.findViewById(R.id.stays_search)
        txtDateRange = view.findViewById(R.id.txt_DateRange)
        roomClientsContainer.setOnClickListener {
            roomClientBottomSheet.show(this.childFragmentManager,"Room Clients Bottom Sheet Dialog")
        }
        searchContainer.setOnClickListener {
            var intent : Intent = Intent(activity,StaysSearchActivity::class.java)
            startActivity(intent)
        }
        searchButton = view.findViewById<AppCompatButton>(R.id.btn_search)
        searchButton.setOnClickListener {
            val intent = Intent(requireContext(),LocationActivity::class.java)
            intent.putExtra("Location Request Model",locationRequestModel)
            startActivity(intent)
        }
        calendarContainer = view.findViewById<RelativeLayout>(R.id.stays_week)
        implementDateRangePicker()
        locationVM = ViewModelProvider(
            this,
            LocationViewModelFactory(LocationRepository())
        )[LocationVM::class.java]

        // Inflate the layout for this fragment
        return view
    }

    private fun implementDateRangePicker() {
        var calendar  = Calendar.getInstance()
        var today = calendar.timeInMillis
        calendar.add(Calendar.DAY_OF_MONTH,1)
        var tomorrow = calendar.timeInMillis
        var selectedRange : androidx.core.util.Pair<Long,Long>
        selectedRange = androidx.core.util.Pair(today,tomorrow)
        var start = Date(selectedRange.first)
        var end = Date(selectedRange.second)
        val dateFormat = SimpleDateFormat("E, dd 'thg' MM", Locale("vi", "VN"))
        txtDateRange.text = "${dateFormat.format(start)} - ${dateFormat.format(end)}"
        calendarContainer.setOnClickListener {
            var today = Calendar.getInstance()
            var nextYear = Calendar.getInstance()
            nextYear.add(Calendar.YEAR,1)
            nextYear.set(Calendar.MONTH,Calendar.getInstance().get(Calendar.MONTH))
            val constraintsBuilder = CalendarConstraints.Builder()
            constraintsBuilder.setStart(today.timeInMillis)
            constraintsBuilder.setEnd(nextYear.timeInMillis)
            val datePickerBuilder = MaterialDatePicker.Builder.dateRangePicker()
            datePickerBuilder.setCalendarConstraints(constraintsBuilder.build())
            datePickerBuilder.setSelection(selectedRange)
            val datePicker = datePickerBuilder.build()
            datePicker.show(childFragmentManager,"Date range picker")
            // Setting up the event for when ok is clicked
            datePicker.addOnPositiveButtonClickListener {
                selectedRange = it
                val first = (selectedRange as Pair<Long,Long>)?.first
                val second = (selectedRange as Pair<Long,Long>)?.second
                first?.let { start.time = first }
                second?.let{end.time=second}
                locationRequestModel.Checkin = Instant.ofEpochSecond(first!!).atZone(ZoneId.systemDefault()).toLocalDateTime()
                locationRequestModel.Checkout = Instant.ofEpochSecond(second!!).atZone(ZoneId.systemDefault()).toLocalDateTime()
                txtDateRange.text = "${dateFormat.format(start)} - ${dateFormat.format(end)}"
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