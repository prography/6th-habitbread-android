package com.example.habitbread.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.habitbread.R
import com.example.habitbread.data.Habits
import com.example.habitbread.ui.activity.DetailActivity
import kotlinx.android.synthetic.main.item_habit.view.*

class HabitListAdapter(private val context: Context?) : RecyclerView.Adapter<HabitListAdapter.HabitListViewHolder>() {

    var data : List<Habits>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitListViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_habit, parent, false)
        return HabitListViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        if(data === null) return 0
        else return data!!.size
    }

    override fun onBindViewHolder(holder: HabitListViewHolder, position: Int) {
        holder.bind(data!![position])
    }

    inner class HabitListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tv_habitName: TextView = itemView.textView_habitName
        val tv_description: TextView = itemView.textView_description

        fun bind(data: Habits){
            tv_habitName.text = data.habitName
            tv_description.text = data.description

            itemView.button_habit.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("habitId", data.habitId)
                Log.d("HabitBread", data.habitId.toString());
                context?.startActivity(intent)
            }
        }
    }

    fun setAdapterData(habitData: List<Habits>?){
        data = habitData
        notifyDataSetChanged()
    }
}