package com.example.habitbread

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_my_habits.*

class MyHabits : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_habits, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showHabitRegisterPage()
    }

    private fun showHabitRegisterPage(){
        button_registerHabit.setOnClickListener {
            Toast.makeText(activity, "등록 버튼 눌림", Toast.LENGTH_SHORT).show()
        }
    }
}