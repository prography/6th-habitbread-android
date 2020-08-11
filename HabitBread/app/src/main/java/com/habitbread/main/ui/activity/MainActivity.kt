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
        initDestination()
        if (BaseApplication.preferences.isTokenRegistered) {
            PushUtils().register()
        }
    }

    private fun initDestination() {
        val navController = findNavController(R.id.nav_host_fragment)
        navController.addOnDestinationChangedListener{controller, destination, arguments ->
            if (isOnBottomNavigation(destination.id)) {
                main_bottom_navigation.visibility = View.VISIBLE
                main_bottom_navigation.setupWithNavController(navController)
                navController.popBackStack()
            } else {
                main_bottom_navigation.visibility = View.GONE
            }
        }
    }

    private fun isOnBottomNavigation(destinationId: Int) : Boolean{
        return destinationId == R.id.myHabits || destinationId == R.id.ranking || destinationId == R.id.account;
    }
}