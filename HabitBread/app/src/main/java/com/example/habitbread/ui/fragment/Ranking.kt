package com.example.habitbread.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.habitbread.R
import com.example.habitbread.`interface`.UpdateFinishHandler
import com.example.habitbread.adapter.HabitListAdapter
import com.example.habitbread.adapter.RankListAdapter
import com.example.habitbread.ui.viewModel.HabitViewModel
import com.example.habitbread.ui.viewModel.RankingViewModel
import kotlinx.android.synthetic.main.fragment_ranking.*
import kotlinx.android.synthetic.main.item_rank.*

class Ranking : Fragment() {
    private lateinit var recyclerview_rankList : RecyclerView
    private lateinit var recyclerview_adapter: RankListAdapter
    val rankingViewModel : RankingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ranking, container, false)
        recyclerview_rankList = view.findViewById(R.id.recyclerview_rankingList)
        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rankingViewModel.getAllRanks()
        initRecyclerView();
        rankingViewModel.rankingData.observe(viewLifecycleOwner, Observer {
            recyclerview_adapter.setAdapterData(it)
            if (it.user != null) {
                val percentage = (it.user.rank.toInt()!!.div(it.userTotalCount)).times(100)
                textview_my_rank_percent_above.visibility = View.VISIBLE
                textview_my_rank_percent.visibility = View.VISIBLE
                textview_my_rank_percent.text = percentage.toString()
                textview_my_rank_with_total.text = getString(R.string.totalRanking, it.userTotalCount, it.user.rank)
            } else {
                textview_my_rank_percent_above.visibility = View.INVISIBLE
                textview_my_rank_percent.visibility = View.INVISIBLE
                textview_my_rank_with_total.text = "아직 점수가 산정되지 않았습니다. 잠시만 기다려주세요!"
            }
        })
    }

    private fun initRecyclerView() {
            recyclerview_adapter = RankListAdapter(context)
            recyclerview_rankList.adapter = recyclerview_adapter
            recyclerview_rankList.layoutManager = LinearLayoutManager(context)
    }
}