package com.example.habitbread.`interface`

import com.example.habitbread.data.Ranking

interface RankHandler {
    fun onResult(myRank: Ranking, totalCount: Int, allRanks : List<Ranking>)
}