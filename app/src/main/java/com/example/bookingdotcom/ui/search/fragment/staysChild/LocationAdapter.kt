package com.example.bookingdotcom.ui.search.fragment.staysChild

import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.bookingdotcom.model.Location
import com.example.myapplication.R

class LocationAdapter(var dataList : MutableList<Location>) : RecyclerView.Adapter<LocationAdapter.ViewHolder>() {
    class ViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView){
        var locationName = itemView.findViewById<TextView>(R.id.txt_locationname)
        var poster = itemView.findViewById<AppCompatImageView>(R.id.img_poster)
        var layout = itemView.findViewById<LinearLayout>(R.id.layout)

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_location,parent,false)
        return ViewHolder(view)
    }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            var item = dataList[position]
            if(item!=null)
            {
                holder.locationName.text = item.locationName
                Glide.with(holder.itemView.context).load(item.poster).transition(
                    DrawableTransitionOptions.withCrossFade()).into(holder.poster)
            }
            holder.layout.setOnClickListener {
                var intent = Intent(holder.itemView.context,LocationDetailActivity::class.java)
                intent.putExtra("Location",item)
                holder.itemView.context.startActivity(intent)
            }
        }

    override fun getItemCount(): Int {
        return dataList.size?:0
    }

}