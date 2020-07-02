package com.example.habitbread.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.habitbread.R
import kotlinx.android.synthetic.main.fragment_account.*

class Account : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_account, container, false);
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setOnClickListener();
    }

    private fun setOnClickListener() {
        imageButton_ask.setOnClickListener {
            Log.d("HabitBread", "Nothing");
        }
        imageButton_change_nickname.setOnClickListener {
            Log.d("HabitBread", "ChangeNickname")
        }
        imageButton_delete_account.setOnClickListener {
            Log.d("HabitBread", "Delete Account")
        }
        imageButton_logout.setOnClickListener {
            Log.d("HabitBread", "Logout")
        }
        imageButton_ask.setOnClickListener {
            Log.d("HabitBread", "ask")
        }
        imageButton_change_info.setOnClickListener {
            Log.d("HabitBread", "Change Info")
        }
    }
}