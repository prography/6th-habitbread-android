package com.example.habitbread.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.habitbread.R
import com.example.habitbread.adapter.BakeryAdapter
import com.example.habitbread.data.BreadsData
import com.example.habitbread.ui.viewModel.BakeryViewModel
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
    private val bakeryViewModel: BakeryViewModel by viewModels()
    private val breadsData: BreadsData = BreadsData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bakery)
        initRecyclerView()
        setBreads()
    }

    private fun initRecyclerView() {
        //Level1
        rvBakeryLevel1 = findViewById(R.id.recyclerView_level1)
        rvBakeryAdapterLevel1 = BakeryAdapter(this)
        rvBakeryLevel1.adapter = rvBakeryAdapterLevel1
        rvBakeryLevel1.layoutManager = GridLayoutManager(this, 3)
        rvBakeryLevel1.addItemDecoration(GridViewDecoration(3, 20, true))

        //Level2
        rvBakeryLevel2 = findViewById(R.id.recyclerView_level2)
        rvBakeryAdapterLevel2 = BakeryAdapter(this)
        rvBakeryLevel2.adapter = rvBakeryAdapterLevel2
        rvBakeryLevel2.layoutManager = GridLayoutManager(this, 3)
        rvBakeryLevel2.addItemDecoration(GridViewDecoration(3, 20, true))

        //Level3
        rvBakeryLevel3 = findViewById(R.id.recyclerView_level3)
        rvBakeryAdapterLevel3 = BakeryAdapter(this)
        rvBakeryLevel3.adapter = rvBakeryAdapterLevel3
        rvBakeryLevel3.layoutManager = GridLayoutManager(this, 3)
        rvBakeryLevel3.addItemDecoration(GridViewDecoration(3, 20, true))

        //Level4
        rvBakeryLevel4 = findViewById(R.id.recyclerView_level4)
        rvBakeryAdapterLevel4 = BakeryAdapter(this)
        rvBakeryLevel4.adapter = rvBakeryAdapterLevel4
        rvBakeryLevel4.layoutManager = GridLayoutManager(this, 3)
        rvBakeryLevel4.addItemDecoration(GridViewDecoration(3, 20, true))
    }

    private fun setBreads() {
        bakeryViewModel.getBreads()
        bakeryViewModel.breadsData.observe(this, Observer {
            val level1ItemIds: MutableList<Int> = mutableListOf()
            val level2ItemIds: MutableList<Int> = mutableListOf()
            val level3ItemIds: MutableList<Int> = mutableListOf()
            val level4ItemIds: MutableList<Int> = mutableListOf()
            if(it == null) {
                rvBakeryAdapterLevel1.setAdapterData(breadsData.level1, level1ItemIds)
                rvBakeryAdapterLevel2.setAdapterData(breadsData.level2, level2ItemIds)
                rvBakeryAdapterLevel3.setAdapterData(breadsData.level3, level3ItemIds)
                rvBakeryAdapterLevel4.setAdapterData(breadsData.level4, level4ItemIds)
            }else {
                for(i in 0..it.size-1) {
                    if(breadsData.level1Ids.contains(it[i].item.itemId)) {
                        level1ItemIds.add(it[i].item.itemId)
                    }
                    if(breadsData.level2Ids.contains(it[i].item.itemId)) {
                        level2ItemIds.add(it[i].item.itemId)
                    }
                    if(breadsData.level3Ids.contains(it[i].item.itemId)) {
                        level3ItemIds.add(it[i].item.itemId)
                    }
                    if(breadsData.level4Ids.contains(it[i].item.itemId)) {
                        level4ItemIds.add(it[i].item.itemId)
                    }
                }
                rvBakeryAdapterLevel1.setAdapterData(breadsData.level1, level1ItemIds)
                rvBakeryAdapterLevel2.setAdapterData(breadsData.level2, level2ItemIds)
                rvBakeryAdapterLevel3.setAdapterData(breadsData.level3, level3ItemIds)
                rvBakeryAdapterLevel4.setAdapterData(breadsData.level4, level4ItemIds)
            }
            rvBakeryAdapterLevel1.notifyDataSetChanged()
            rvBakeryAdapterLevel2.notifyDataSetChanged()
            rvBakeryAdapterLevel3.notifyDataSetChanged()
            rvBakeryAdapterLevel4.notifyDataSetChanged()
        })
    }
}