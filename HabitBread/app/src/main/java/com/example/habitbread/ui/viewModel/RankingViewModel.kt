package com.example.habitbread.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.habitbread.`interface`.RankHandler
import com.example.habitbread.`interface`.UpdateFinishHandler
import com.example.habitbread.data.Ranking
import com.example.habitbread.repository.HabitRepository

class RankingViewModel : ViewModel() {
    private var rankListData: List<Ranking>? = null;
    private var rank : Ranking? = null;
    private var userTotalCount : Int? = null;

    companion object {
        private val instance = RankingViewModel();
        fun getInstance() : RankingViewModel = instance
    }

    fun getAllRanks() : List<Ranking> {
        return rankListData!!;
    }

    fun getMyRank() : Ranking {
        return rank!!;
    }

    fun getTotalCount() : Int {
        return userTotalCount!!
    }

    fun init(handler: UpdateFinishHandler) {
        HabitRepository().getAllRanks(object : RankHandler {
            override fun onResult(myRank: Ranking, totalCount: Int, allRanks: List<Ranking>) {
                rank = myRank;
                rankListData = allRanks;
                userTotalCount = totalCount;
                handler.onUpdated();
            }
        })
    }
}