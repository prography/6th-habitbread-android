package com.habitbread.main.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.habitbread.main.R
import com.habitbread.main.adapter.MainViewPager
import com.habitbread.main.ui.fragment.Account
import com.habitbread.main.ui.fragment.MyHabits
import com.habitbread.main.ui.fragment.Ranking
import com.habitbread.main.base.BaseApplication
import com.habitbread.main.util.PushUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewPager()
        initBottomNavigation()
    }

    override fun onResume() {
        super.onResume()
        if (BaseApplication.preferences.isTokenRegistered) {
            PushUtils().register()
        }
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

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
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
                R.id.ranking -> {
                    setPageIndex(1)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.account -> {
                    setPageIndex(2)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    setPageIndex(0)
                    return@setOnNavigationItemSelectedListener true
                }
            }
        }
    }

    private fun setPageIndex(index: Int) {
        viewPager.currentItem = index
    }
}