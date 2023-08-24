package com.example.bookingdotcom.ui.search.fragment.staysChild

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        var rating = itemView.findViewById<TextView>(R.id.txt_scores)
        var discount = itemView.findViewById<TextView>(R.id.txt_discount)
        var price = itemView.findViewById<TextView>(R.id.txt_price)
        var hotelRoom = itemView.findViewById<TextView>(R.id.txt_hotelroom)
        var review = itemView.findViewById<TextView>(R.id.txt_reviews)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_location,parent,false)
        return ViewHolder(view)
    }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = dataList[position]

                if(item.discount!! <=0)
                {
                    holder.discount.visibility = View.INVISIBLE
                }
                val hotelRoom = item.room?.roomType?.roomTypeBeds
                if(hotelRoom?.size!!>0)
                {
                    holder.hotelRoom.text = (hotelRoom!!.size>0).let { "Hotel room : ${hotelRoom.get(0).quantity} ${hotelRoom.get(0).bedType!!.bedTypeName} bed" }
                }
                holder.discount.text = item.room!!.price.toString()
                holder.review.text = item.ratingQuantity.toString() + " reviews"
                holder.price.text = (item.room!!.price!!.times(100-item.discount!!)).toString()
                holder.locationName.text = item.locationName
                holder.rating.text = String.format("%.1f",item.rating)
                Glide.with(holder.itemView.context).load(item.poster).transition(
                    DrawableTransitionOptions.withCrossFade()).into(holder.poster)

            holder.layout.setOnClickListener {
                val intent = Intent(holder.itemView.context,LocationDetailActivity::class.java)
                intent.putExtra("Location",item)
                holder.itemView.context.startActivity(intent)
            }
        }

    override fun getItemCount(): Int {
        return dataList.size
    }

}