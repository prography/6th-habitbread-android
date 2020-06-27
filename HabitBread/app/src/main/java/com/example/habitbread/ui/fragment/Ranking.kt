package com.example.habitbread.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.habitbread.R
import com.example.habitbread.`interface`.UpdateFinishHandler
import com.example.habitbread.adapter.RankListAdapter
import com.example.habitbread.ui.viewModel.RankingViewModel
import kotlinx.android.synthetic.main.fragment_ranking.*

class Ranking : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ranking, container, false)
        return view;
    }
}