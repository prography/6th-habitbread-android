package com.example.habitbread.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.habitbread.R
import kotlinx.android.synthetic.main.item_alarm_weekday.view.*

class AlarmWeekAdapter(private val context: Context?): RecyclerView.Adapter<AlarmWeekAdapter.AlarmWeekViewHolder>() {

    var data = listOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmWeekViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_alarm_weekday, parent, false)
        return AlarmWeekViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: AlarmWeekViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class AlarmWeekViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvDay = itemView.checkedTextView_weekday

        fun bind(data: String){
            tvDay.text = data
            itemView.setOnClickListener {
                tvDay.toggle()
            }
        }
    }
}