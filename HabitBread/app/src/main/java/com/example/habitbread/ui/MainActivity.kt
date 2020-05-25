package com.example.habitbread.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.habitbread.R
import com.example.habitbread.base.BaseActivity
import com.example.habitbread.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel: HabitViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dataBinding.viewModel = viewModel;
        initBottomNavigation()
    }

    private fun initBottomNavigation(){
        replaceFragment(MyHabits())
        main_bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.ranking -> {
                    replaceFragment(Ranking())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.account -> {
                    replaceFragment(Account())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    replaceFragment(MyHabits())
                    return@setOnNavigationItemSelectedListener true
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_frameLayout, fragment)
        fragmentTransaction.commit()
    }
}