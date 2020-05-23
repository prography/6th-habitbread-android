package com.example.habitbread

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HabitListAdapter(private val context: Context) : RecyclerView.Adapter<HabitListViewHolder>() {

    var data = listOf<HabitListData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitListViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_habit, parent, false)
        return HabitListViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: HabitListViewHolder, position: Int) {
        holder.bind(data[position])
    }
}