package com.example.habitbread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_my_habits.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
