package com.example.habitbread.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.habitbread.R
import com.example.habitbread.ui.fragment.Account
import com.example.habitbread.ui.fragment.MyHabits
import com.example.habitbread.ui.fragment.Ranking
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onResume() {
        super.onResume()
        getFCMCurrentToken()
        FirebaseMessaging.getInstance().isAutoInitEnabled = true
    }

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