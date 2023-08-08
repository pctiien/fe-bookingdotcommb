package com.example.bookingdotcom.ui.search.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.core.util.Pair
import com.example.bookingdotcom.ui.search.RangePickerBottomSheetFragment
import com.example.bookingdotcom.ui.search_activity.StaysSearchActivity
import com.example.myapplication.R
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_stays, container, false)
        searchContainer = view.findViewById<RelativeLayout>(R.id.stays_search)
        searchContainer.setOnClickListener {
            var intent : Intent = Intent(activity,StaysSearchActivity::class.java)
            startActivity(intent)
        }
        calendarContainer = view.findViewById<RelativeLayout>(R.id.stays_week)
        val datePickerFragment = RangePickerBottomSheetFragment()

        calendarContainer.setOnClickListener {
            datePickerFragment.show(childFragmentManager, datePickerFragment.tag)
        }


        // Inflate the layout for this fragment
        return view
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