package com.example.habitbread.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.habitbread.R
import kotlinx.android.synthetic.main.item_rank.view.*

class RankListAdapter(private val context: Context?) : RecyclerView.Adapter<RankListAdapter.RankingListViewHolder>() {

    var data : List<Ranking> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingListViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_rank, parent, false)
        return RankingListViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RankingListViewHolder, position: Int) {
        holder.bind(data[position])
    }


    inner class RankingListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tv_rank : TextView = itemView.textview_rank_num
        val tv_nickname : TextView = itemView.textview_rank_nickname
        val tv_exp : TextView = itemView.textview_rank_exp
        val tv_achievement : TextView = itemView.textview_rank_achievement

        fun bind(data: Ranking){
            tv_rank.text = data.rank
            tv_nickname.text = data.userName
            tv_exp.text = data.exp.toString()
            tv_achievement.text = data.achievement.toString()
        }
    }
}