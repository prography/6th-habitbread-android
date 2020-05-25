package com.example.habitbread.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.habitbread.ui.adapter.HabitListAdapter
import com.example.habitbread.data.HabitListData
import com.example.habitbread.R
import com.example.habitbread.base.BaseFragment
import com.example.habitbread.databinding.FragmentMyHabitsBinding
import kotlinx.android.synthetic.main.fragment_my_habits.*

class MyHabits : BaseFragment<FragmentMyHabitsBinding>(R.layout.fragment_my_habits) {

    private lateinit var recyclerview_habitList: RecyclerView
    private lateinit var recyclerview_adapter: HabitListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=  super.onCreateView(inflater, container, savedInstanceState)
        recyclerview_habitList = view!!.findViewById(R.id.recyclerView_habitlist)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        button_add.setOnClickListener {
            val registrationBottomSheet =
                RegistrationBottomSheet()
            registrationBottomSheet.show(childFragmentManager, "showBottomSheet")
        }
    }

    private fun initRecyclerView() {
        recyclerview_adapter =
            HabitListAdapter(context)
        recyclerview_habitList.adapter = recyclerview_adapter
        recyclerview_habitList.layoutManager = LinearLayoutManager(context)
        recyclerview_adapter.data = initialList
        recyclerview_adapter.notifyDataSetChanged()
    }

    private val initialList = listOf(
        HabitListData(
            habitName = "운동하자 초희야",
            period = "2020.05.22 ~",
            percentage = "56"
        ),
        HabitListData(
            habitName = "물 2L 마시기",
            period = "2020.05.22 ~",
            percentage = "56"
        ),
        HabitListData(
            habitName = "자기 전에 책 10분 보기",
            period = "2020.05.22 ~",
            percentage = "56"
        ),
        HabitListData(
            habitName = "아직 9시 기상하기",
            period = "2020.05.22 ~",
            percentage = "56"
        ),
        HabitListData(
            habitName = "손톱 기르기!!!!!!",
            period = "2020.05.22 ~",
            percentage = "56"
        )
    )
}

