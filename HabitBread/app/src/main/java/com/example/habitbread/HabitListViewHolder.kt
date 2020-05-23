package com.example.habitbread

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HabitListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val tv_habitName: TextView = itemView.findViewById(R.id.textView_habitName)
    val tv_period: TextView = itemView.findViewById(R.id.textView_period)
    val tv_percentage: TextView = itemView.findViewById(R.id.textView_percentage)

    fun bind(data: HabitListData){
        tv_habitName.text = data.habitName
        tv_period.text = data.period
        tv_percentage.text = data.percentage + "%"
    }

}