package com.example.habitbread.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.habitbread.R
import com.example.habitbread.data.HabitListData
import com.example.habitbread.ui.activity.DetailActivity
import kotlinx.android.synthetic.main.item_habit.view.*

class HabitListAdapter(private val context: Context?) : RecyclerView.Adapter<HabitListAdapter.HabitListViewHolder>() {

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

    inner class HabitListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tv_habitName: TextView = itemView.textView_habitName
        val tv_period: TextView = itemView.textView_period
        val tv_percentage: TextView = itemView.textView_percentage

        fun bind(data: HabitListData){
            tv_habitName.text = data.habitName
            tv_period.text = data.period
            tv_percentage.text = data.percentage + "%"

            itemView.button_habit.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                context?.startActivity(intent)
            }
        }
    }
}