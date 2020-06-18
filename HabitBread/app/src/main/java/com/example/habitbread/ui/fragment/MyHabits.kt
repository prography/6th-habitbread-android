package com.example.habitbread.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.habitbread.adapter.HabitListAdapter
import com.example.habitbread.R
import com.example.habitbread.data.NewHabitReq
import com.example.habitbread.ui.viewModel.HabitViewModel
import kotlinx.android.synthetic.main.fragment_my_habits.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MyHabits : Fragment() {

    private lateinit var recyclerview_habitList: RecyclerView
    private lateinit var recyclerview_adapter: HabitListAdapter
    val habitViewModel: HabitViewModel by viewModels()

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
        habitViewModel.getAllList()
        initRecyclerView()
        habitViewModel.rvData.observe(viewLifecycleOwner, Observer {
            recyclerview_adapter.setAdapterData(it)
        })
        onShowModal()
    }

    private fun initRecyclerView() {
        recyclerview_adapter = HabitListAdapter(context)
        recyclerview_habitList.adapter = recyclerview_adapter
        recyclerview_habitList.layoutManager = LinearLayoutManager(context)
    }

    private fun onShowModal(){
        button_add.setOnClickListener {
            val registrationBottomSheet = RegistrationBottomSheet()
            registrationBottomSheet.show(parentFragmentManager, "showBottomSheet")
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onBottomSheetDoneEvent(modalPost: ModalPost){
        val body: NewHabitReq = NewHabitReq(title = modalPost.title, category = modalPost.category, dayOfWeek = modalPost.dayOfWeek, alarmTime = modalPost.alarmTime)
        habitViewModel.postHabit(body)
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
    }
}