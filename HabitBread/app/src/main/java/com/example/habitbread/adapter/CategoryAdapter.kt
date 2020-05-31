package com.example.habitbread.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.habitbread.R
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter(private val context: Context?) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(){

    var data = listOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvCategory: CheckedTextView = itemView.checkedTextView

        fun bind(data : String){
            tvCategory.text = data
            tvCategory.setOnClickListener {
                tvCategory.toggle()
            }
        }
    }
}