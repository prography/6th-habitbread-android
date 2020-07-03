package com.example.habitbread.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.habitbread.R
import com.example.habitbread.adapter.BakeryAdapter
import com.example.habitbread.data.BreadResponse
import com.example.habitbread.data.ItemResponse
import com.example.habitbread.util.GridViewDecoration

class BakeryActivity : AppCompatActivity() {

    private lateinit var rvBakeryLevel1: RecyclerView
    private lateinit var rvBakeryLevel2: RecyclerView
    private lateinit var rvBakeryLevel3: RecyclerView
    private lateinit var rvBakeryLevel4: RecyclerView
    private lateinit var rvBakeryAdapterLevel1: BakeryAdapter
    private lateinit var rvBakeryAdapterLevel2: BakeryAdapter
    private lateinit var rvBakeryAdapterLevel3: BakeryAdapter
    private lateinit var rvBakeryAdapterLevel4: BakeryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bakery)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        //Level1
        rvBakeryLevel1 = findViewById(R.id.recyclerView_level1)
        rvBakeryAdapterLevel1 = BakeryAdapter(this)
        rvBakeryLevel1.adapter = rvBakeryAdapterLevel1
        rvBakeryLevel1.layoutManager = GridLayoutManager(this, 3)
        rvBakeryLevel1.addItemDecoration(GridViewDecoration(3, 20, true))
        rvBakeryAdapterLevel1.data = listOf(
            BreadResponse(
                userItemId = 1,
                createdAt = "dafsdf",
                item = (ItemResponse(itemId = 1, name = "초희", description = "dfadsf", level = 1, img = "dfs"))
            ),
            BreadResponse(
                userItemId = 1,
                createdAt = "dafsdf",
                item = (ItemResponse(itemId = 1, name = "초희", description = "dfadsf", level = 1, img = "dfs"))
            ),
            BreadResponse(
                userItemId = 1,
                createdAt = "dafsdf",
                item = (ItemResponse(itemId = 1, name = "초희", description = "dfadsf", level = 1, img = "dfs"))
            ),
            BreadResponse(
                userItemId = 1,
                createdAt = "dafsdf",
                item = (ItemResponse(itemId = 1, name = "초희", description = "dfadsf", level = 1, img = "dfs"))
            ),
            BreadResponse(
                userItemId = 1,
                createdAt = "dafsdf",
                item = (ItemResponse(itemId = 1, name = "초희", description = "dfadsf", level = 1, img = "dfs"))
            )
        )
        rvBakeryAdapterLevel1.notifyDataSetChanged()

        //Level2
        rvBakeryLevel2 = findViewById(R.id.recyclerView_level2)
        rvBakeryAdapterLevel2 = BakeryAdapter(this)
        rvBakeryLevel2.adapter = rvBakeryAdapterLevel2
        rvBakeryLevel2.layoutManager = GridLayoutManager(this, 3)
        rvBakeryLevel2.addItemDecoration(GridViewDecoration(3, 20, true))
        rvBakeryAdapterLevel2.data = listOf(
            BreadResponse(
                userItemId = 1,
                createdAt = "dafsdf",
                item = (ItemResponse(itemId = 1, name = "초희", description = "dfadsf", level = 1, img = "dfs"))
            ),
            BreadResponse(
                userItemId = 1,
                createdAt = "dafsdf",
                item = (ItemResponse(itemId = 1, name = "초희", description = "dfadsf", level = 1, img = "dfs"))
            ),
            BreadResponse(
                userItemId = 1,
                createdAt = "dafsdf",
                item = (ItemResponse(itemId = 1, name = "초희", description = "dfadsf", level = 1, img = "dfs"))
            ),
            BreadResponse(
                userItemId = 1,
                createdAt = "dafsdf",
                item = (ItemResponse(itemId = 1, name = "초희", description = "dfadsf", level = 1, img = "dfs"))
            ),
            BreadResponse(
                userItemId = 1,
                createdAt = "dafsdf",
                item = (ItemResponse(itemId = 1, name = "초희", description = "dfadsf", level = 1, img = "dfs"))
            )
        )
        rvBakeryAdapterLevel2.notifyDataSetChanged()

        //Level3
        rvBakeryLevel3 = findViewById(R.id.recyclerView_level3)
        rvBakeryAdapterLevel3 = BakeryAdapter(this)
        rvBakeryLevel3.adapter = rvBakeryAdapterLevel3
        rvBakeryLevel3.layoutManager = GridLayoutManager(this, 3)
        rvBakeryLevel3.addItemDecoration(GridViewDecoration(3, 20, true))
        rvBakeryAdapterLevel3.data = listOf(
            BreadResponse(
                userItemId = 1,
                createdAt = "dafsdf",
                item = (ItemResponse(itemId = 1, name = "초희", description = "dfadsf", level = 1, img = "dfs"))
            ),
            BreadResponse(
                userItemId = 1,
                createdAt = "dafsdf",
                item = (ItemResponse(itemId = 1, name = "초희", description = "dfadsf", level = 1, img = "dfs"))
            ),
            BreadResponse(
                userItemId = 1,
                createdAt = "dafsdf",
                item = (ItemResponse(itemId = 1, name = "초희", description = "dfadsf", level = 1, img = "dfs"))
            ),
            BreadResponse(
                userItemId = 1,
                createdAt = "dafsdf",
                item = (ItemResponse(itemId = 1, name = "초희", description = "dfadsf", level = 1, img = "dfs"))
            ),
            BreadResponse(
                userItemId = 1,
                createdAt = "dafsdf",
                item = (ItemResponse(itemId = 1, name = "초희", description = "dfadsf", level = 1, img = "dfs"))
            )
        )
        rvBakeryAdapterLevel3.notifyDataSetChanged()

        //Level4
        rvBakeryLevel4 = findViewById(R.id.recyclerView_level4)
        rvBakeryAdapterLevel4 = BakeryAdapter(this)
        rvBakeryLevel4.adapter = rvBakeryAdapterLevel4
        rvBakeryLevel4.layoutManager = GridLayoutManager(this, 3)
        rvBakeryLevel4.addItemDecoration(GridViewDecoration(3, 20, true))
        rvBakeryAdapterLevel4.data = listOf(
            BreadResponse(
                userItemId = 1,
                createdAt = "dafsdf",
                item = (ItemResponse(itemId = 1, name = "초희", description = "dfadsf", level = 1, img = "dfs"))
            ),
            BreadResponse(
                userItemId = 1,
                createdAt = "dafsdf",
                item = (ItemResponse(itemId = 1, name = "초희", description = "dfadsf", level = 1, img = "dfs"))
            ),
            BreadResponse(
                userItemId = 1,
                createdAt = "dafsdf",
                item = (ItemResponse(itemId = 1, name = "초희", description = "dfadsf", level = 1, img = "dfs"))
            ),
            BreadResponse(
                userItemId = 1,
                createdAt = "dafsdf",
                item = (ItemResponse(itemId = 1, name = "초희", description = "dfadsf", level = 1, img = "dfs"))
            ),
            BreadResponse(
                userItemId = 1,
                createdAt = "dafsdf",
                item = (ItemResponse(itemId = 1, name = "초희", description = "dfadsf", level = 1, img = "dfs"))
            )
        )
        rvBakeryAdapterLevel4.notifyDataSetChanged()
    }
}