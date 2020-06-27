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
import kotlinx.android.synthetic.main.item_rank.*

class Ranking : Fragment() {

    private lateinit var recyclerView_rankList: RecyclerView
    private lateinit var recyclerView_adapter: RankListAdapter

    private val rankingViewModel: RankingViewModel = RankingViewModel.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ranking, container, false)
        recyclerView_rankList = view.findViewById(R.id.recyclerview_rankingList)
        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView();
    }

    override fun onResume() {
        super.onResume();
        getRanks()
    }

    private fun getRanks() {
        rankingViewModel.init(object : UpdateFinishHandler {
            override fun onUpdated() {
                val list = rankingViewModel.getAllRanks();
                recyclerView_adapter.data = list;
                setMyRank();
                recyclerView_adapter.notifyDataSetChanged();
            }
        })
    }

    private fun setMyRank() {
        val myData = rankingViewModel.getMyRank();
        textview_my_rank_percent.text = (myData.rank.toInt() / rankingViewModel.getTotalCount() * 100).toString()
        textview_rank_exp.text = myData.exp.toString()
        textview_my_rank_with_total.text = String.format("%d / %s", rankingViewModel.getTotalCount(), myData.rank);
    }

    private fun initRecyclerView() {
        recyclerView_adapter = RankListAdapter(context)
        recyclerView_rankList.adapter = recyclerView_adapter
        recyclerView_rankList.layoutManager = LinearLayoutManager(context)
    }
}