package com.example.habitbread.`interface`

interface RankHandler {
    fun onResult(myRank: Ranking, totalCount: Int, allRanks : List<Ranking>)
}