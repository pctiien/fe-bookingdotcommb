package com.example.bookingdotcom.ui.search.fragment.staysChild

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingdotcom.model.Rating
import com.example.myapplication.R

class LocationRatingAdapter(private val dataList : MutableList<Rating>) : RecyclerView.Adapter<LocationRatingAdapter.ViewHolder>() {
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var userName: TextView = itemView.findViewById(R.id.txt_username)
        var userCountry : TextView = itemView.findViewById(R.id.txt_usercountry)
        var comment : TextView = itemView.findViewById(R.id.txt_comment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_rating,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.userName.text = item.userName
        holder.userCountry.text = item.userCountry
        holder.comment.text = "\""+item.comment+"\""
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}