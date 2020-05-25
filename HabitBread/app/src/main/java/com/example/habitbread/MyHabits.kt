package com.example.habitbread

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_my_habits.*

class MyHabits : Fragment() {

    private lateinit var recyeclerView_habitList: RecyclerView
    private lateinit var recyclerView_adapter: HabitListAdapter
    private lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_my_habits, container, false)
        recyeclerView_habitList = view.findViewById(R.id.recyclerView_habitlist)
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        button_add.setOnClickListener {
            val registrationBottomSheet = RegistrationBottomSheet()
            registrationBottomSheet.show(childFragmentManager, "showBottomSheet")
        }
    }

    private fun initRecyclerView(){
        recyclerView_adapter = HabitListAdapter(mContext)
        recyeclerView_habitList.adapter = recyclerView_adapter
        recyeclerView_habitList.layoutManager = LinearLayoutManager(mContext)
        recyclerView_adapter.data = listOf(
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
        recyclerView_adapter.notifyDataSetChanged()
    }
}