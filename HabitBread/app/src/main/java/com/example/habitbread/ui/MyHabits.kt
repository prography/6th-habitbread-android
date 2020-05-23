package com.example.habitbread.ui

import android.os.Bundle
import com.example.habitbread.R
import com.example.habitbread.base.BaseFragment
import com.example.habitbread.databinding.FragmentMyHabitsBinding
import kotlinx.android.synthetic.main.fragment_my_habits.*

class MyHabits : BaseFragment<FragmentMyHabitsBinding>(R.layout.fragment_my_habits) {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        button_add.setOnClickListener {
            val registrationBottomSheet =
                RegistrationBottomSheet()
            registrationBottomSheet.show(childFragmentManager, "showBottomSheet")
        }
    }
}