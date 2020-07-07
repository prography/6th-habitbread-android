package com.example.habitbread.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.habitbread.R
import com.example.habitbread.data.Habits
import com.example.habitbread.ui.activity.DetailActivity
import com.example.habitbread.util.DateCalculation
import kotlinx.android.synthetic.main.item_habit.view.*

class HabitListAdapter(private val context: Context?) : RecyclerView.Adapter<HabitListAdapter.HabitListViewHolder>() {

    var habits : List<Habits>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitListViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_habit, parent, false)
        return HabitListViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        if(habits === null) return 0
        else return habits!!.size
    }

    override fun onBindViewHolder(holder: HabitListViewHolder, position: Int) {
        holder.bind(habits!![position])
    }

    inner class HabitListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tv_habitName: TextView = itemView.textView_habitName
        val tv_description: TextView = itemView.textView_description

        fun bind(data: Habits){
            tv_habitName.text = data.habitName
            tv_description.text = data.description
            if(data.dayOfWeek[DateCalculation().getTodayOfWeek()] == '1') {
                itemView.textView_isToday.text = "오늘"
            }else if(data.dayOfWeek[DateCalculation().getTodayOfWeek()+1] == '1'){
                itemView.textView_isToday.text = "내일"
                itemView.imageView_dot.visibility = View.INVISIBLE
                itemView.textView_isToday.setBackgroundColor(Color.parseColor("#CCFFFFFF"))
                itemView.button_habit.setBackgroundColor(Color.parseColor("#CCFFFFFF"))
            }else {
                itemView.textView_isToday.visibility = View.INVISIBLE
                itemView.imageView_dot.visibility = View.INVISIBLE
                itemView.textView_isToday.setBackgroundColor(Color.parseColor("#CCFFFFFF"))
                itemView.button_habit.setBackgroundColor(Color.parseColor("#CCFFFFFF"))
            }

            // 습관 등록 요일만 표시하기
           for(i in 0..6) {
                if(data.dayOfWeek[i] == '0') {
                    itemView.findViewWithTag<TextView>(i.toString()).visibility = View.GONE
                }
            }

            // 커밋한 날짜 초록색 표시하기
            for(i in 0..data.commitHistory.size-1) {
                val inputDate: String = DateCalculation().convertUTCtoSeoulTime(data.commitHistory[i].createdAt).substring(0, 10)
                val index = DateCalculation().getTodayOfWeekWithDate(inputDate)
                itemView.findViewWithTag<TextView>(index.toString()).setBackgroundResource(R.drawable.background_dayofweek)
                itemView.findViewWithTag<TextView>(index.toString()).setTextColor(Color.parseColor("#FFFFFF"))
                if(index == DateCalculation().getTodayOfWeek()) {
                    itemView.imageView_dot.visibility = View.INVISIBLE
                }
            }

            itemView.button_habit.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("habitId", data.habitId)
                intent.putExtra("habitName", data.habitName)
                intent.putExtra("habitDescription", data.description)
                context?.startActivity(intent)
            }
        }
    }

    fun setAdapterData(habitData: List<Habits>?){
        habits = habitData
        notifyDataSetChanged()
    }
}