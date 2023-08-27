package com.example.bookingdotcom.ui.search.fragment.staysChild

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R

class LocationImgAdapter(val dataList : List<String>) : RecyclerView.Adapter<LocationImgAdapter.ViewHolder>() {
    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {
        var img = itemView.findViewById<AppCompatImageView>(R.id.locationImg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_locationimgs,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        Glide.with(holder.itemView.context).load(item).into(holder.img)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}