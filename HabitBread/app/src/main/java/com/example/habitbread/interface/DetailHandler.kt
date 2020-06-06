package com.example.habitbread.`interface`

import com.example.habitbread.data.DetailResponse

interface DetailHandler {
    fun onResult(handlerList: DetailResponse)
}