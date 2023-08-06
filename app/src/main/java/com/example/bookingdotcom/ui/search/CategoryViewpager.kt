package com.example.bookingdotcom.ui.search

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bookingdotcom.ui.search.fragment.AttractionsFragment
import com.example.bookingdotcom.ui.search.fragment.CarRentalFragment
import com.example.bookingdotcom.ui.search.fragment.StaysFragment
import com.example.bookingdotcom.ui.search.fragment.TaxiFragment

class CategoryViewpager(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when(position)
        {
            0-> StaysFragment()
            1-> CarRentalFragment()
            2->TaxiFragment()
            3->AttractionsFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }

    }
}