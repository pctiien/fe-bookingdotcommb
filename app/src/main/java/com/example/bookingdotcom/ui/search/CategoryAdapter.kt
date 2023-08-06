package com.example.bookingdotcom.ui.search

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import java.util.Objects
import java.util.logging.Logger

class CategoryAdapter() : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    private var onItemClickListener : ((Int)->Unit)?=null
    private var itemList = mutableListOf<Category>()
    init {
        itemList.add(Category("Stays",R.drawable.ic_baseline_single_bed_24,false))
        itemList.add(Category("Car rental",R.drawable.ic_baseline_directions_car_24,false))
        itemList.add(Category("Taxi",R.drawable.ic_baseline_local_taxi_24,false))
        itemList.add(Category("Attractions",R.drawable.ic_baseline_attractions_24,false))
        itemList[0].isChecked = true

    }
    fun setOnItemClickListener(listener :((Int)->Unit)?)
    {
        onItemClickListener = listener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img = itemView.findViewById<ImageView>(R.id.category_icon)
        val cate = itemView.findViewById<TextView>(R.id.cate_name)
        val layout = itemView.findViewById<RelativeLayout>(R.id.layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_category,parent,false)
        return ViewHolder(view);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        if(item!=null)
        {
            if(item.isChecked)
            {
                holder.layout.setBackgroundResource(R.drawable.category_item)
            }else
            {
                holder.layout.background = null
            }
            holder.cate.text = item.cate
            holder.img.setBackgroundResource(item.img)
            holder.itemView.setOnClickListener {
                val pos = holder.adapterPosition
                onItemClickListener?.invoke(pos)
                for(i in 0 until itemCount)
                {
                    if(i==pos){
                        itemList[i].isChecked = true
                        continue
                    }
                    itemList[i].isChecked = false
                }
                notifyDataSetChanged()

            }
        }
    }

    override fun getItemCount(): Int {
        if(itemList!=null) return itemList.size
        return 0
    }
}
data class Category(
    val cate : String,
    val img : Int,
    var isChecked : Boolean
)