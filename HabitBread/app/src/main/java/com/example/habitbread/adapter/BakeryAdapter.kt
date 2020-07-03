package com.example.habitbread.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.habitbread.R
import com.example.habitbread.data.BreadResponse
import kotlinx.android.synthetic.main.item_bread.view.*

class BakeryAdapter(val context: Context): RecyclerView.Adapter<BakeryAdapter.BakeryViewHolder>() {

    var data = listOf<BreadResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BakeryViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_bread, parent, false)
        return BakeryViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: BakeryViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class BakeryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvLevel: TextView = itemView.textView_level
        val ivBread: ImageView = itemView.imageView_bread
        val tvBreadName: TextView = itemView.textView_bread_name

        fun bind(data : BreadResponse) {
            tvLevel.text = data.item.level.toString()
            tvBreadName.text = data.item.name
        }
    }
}