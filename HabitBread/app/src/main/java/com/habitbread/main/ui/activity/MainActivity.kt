package com.habitbread.main.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.habitbread.main.R
import com.habitbread.main.ui.fragment.Account
import com.habitbread.main.ui.fragment.MyHabits
import com.habitbread.main.ui.fragment.Ranking
import com.habitbread.main.base.BaseApplication
import com.habitbread.main.util.PushUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewPager()
        initBottomNavigation()
        initDestination()
        if (BaseApplication.preferences.isTokenRegistered) {
            PushUtils().register()
        }
    }

    private fun initViewPager() {
        val pagerAdapter = MainViewPager(this)
        pagerAdapter.addFragment(MyHabits())
        pagerAdapter.addFragment(Ranking())
        pagerAdapter.addFragment(Account())
        main_viewPager.adapter = pagerAdapter
        main_viewPager.registerOnPageChangeCallback(PageChangeCallback())
    }

    private fun initDestination() {
        val navController = findNavController(R.id.nav_host_fragment)
        navController.addOnDestinationChangedListener{controller, destination, arguments ->
            if (isOnBottomNavigation(destination.id)) {
                main_bottom_navigation.visibility = View.VISIBLE
                main_bottom_navigation.setupWithNavController(navController)
            } else {
                main_bottom_navigation.visibility = View.INVISIBLE
            }
        }
    }

    private fun isOnBottomNavigation(destinationId: Int) : Boolean{
        return destinationId == R.id.myHabits || destinationId == R.id.ranking || destinationId == R.id.account;
    }

    private fun initBottomNavigation() {
        main_bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ranking -> setPageIndex(1)
                R.id.account -> setPageIndex(2)
                else -> setPageIndex(0)
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun setPageIndex(index: Int) {
        main_viewPager.currentItem = index
    }

    private inner class MainViewPager(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        private var fragmentList: ArrayList<Fragment> = arrayListOf()

        fun addFragment(fragment: Fragment) {
            fragmentList.add(fragment)
        }

        override fun getItemCount(): Int {
            return fragmentList.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragmentList[position]
        }
    }


    private inner class PageChangeCallback : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            main_bottom_navigation.selectedItemId = when (position) {
                1 -> R.id.ranking
                2 -> R.id.account
                else -> R.id.myHabits
            }
        }
    }
}