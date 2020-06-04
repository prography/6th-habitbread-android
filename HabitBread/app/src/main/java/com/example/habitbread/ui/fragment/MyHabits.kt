package com.example.habitbread.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.habitbread.adapter.HabitListAdapter
import com.example.habitbread.R
import com.example.habitbread.ui.viewModel.HabitViewModel
import kotlinx.android.synthetic.main.fragment_my_habits.*

class MyHabits : Fragment() {

    private lateinit var recyclerview_habitList: RecyclerView
    private lateinit var recyclerview_adapter: HabitListAdapter

    private val habitViewModel: HabitViewModel = HabitViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_my_habits, container, false)
        recyclerview_habitList = view.findViewById(R.id.recyclerView_habitlist)
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
        recyclerview_adapter = HabitListAdapter(context)
        recyclerview_habitList.adapter = recyclerview_adapter
        recyclerview_habitList.layoutManager = LinearLayoutManager(context)
        recyclerview_adapter.data = habitViewModel.getHabitListData()
        recyclerview_adapter.notifyDataSetChanged()
    }
}