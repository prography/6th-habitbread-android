package com.example.habitbread.`interface`

import com.example.habitbread.data.Ranking

interface RankListHandler {
    fun onResult(myRank: Ranking, totalCount: Int, allRanks : List<Ranking>)
}