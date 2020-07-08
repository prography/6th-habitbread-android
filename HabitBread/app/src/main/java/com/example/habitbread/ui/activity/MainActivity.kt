package com.example.habitbread.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.habitbread.R
import com.example.habitbread.adapter.MainViewPager
import com.example.habitbread.ui.fragment.Account
import com.example.habitbread.ui.fragment.MyHabits
import com.example.habitbread.ui.fragment.Ranking
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
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
        getFCMCurrentToken()
        FirebaseMessaging.getInstance().isAutoInitEnabled = true
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

    private fun getFCMCurrentToken(){
        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener(OnCompleteListener { task ->
            if(!task.isSuccessful){
                Log.w("FCMCurrentTokenTest", "getFCMCurrentToken failed", task.exception)
                return@OnCompleteListener
            }

            // This is a new Token(Instance ID)
            val token = task.result?.token.toString()
            Log.d("FCM_Token", token)
        })
    }
}