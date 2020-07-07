package com.example.habitbread.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import com.example.habitbread.R
import com.example.habitbread.adapter.MainViewPager
import com.example.habitbread.ui.fragment.Account
import com.example.habitbread.ui.fragment.MyHabits
import com.example.habitbread.ui.fragment.Ranking
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewPager()
        initBottomNavigation()
    }

    private fun initViewPager() {
        viewPager = findViewById(R.id.main_viewPager);

        val pagerAdapter = MainViewPager(supportFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        pagerAdapter.addFragment(MyHabits())
        pagerAdapter.addFragment(Ranking())
        pagerAdapter.addFragment(Account())
        viewPager.adapter = pagerAdapter

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
//                TODO("Not yet implemented")
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
//                TODO("Not yet implemented")
            }

            override fun onPageSelected(position: Int) {
                main_bottom_navigation.menu.getItem(position).isChecked = true
            }
        })
    }

    private fun initBottomNavigation() {
        main_bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.myHabits -> {
                    setPageIndex(0)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.ranking -> {
                    setPageIndex(1)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.account -> {
                    setPageIndex(2)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    private fun setPageIndex(index: Int) {
        viewPager.currentItem = index
    }
}