package com.example.habitbread.ui.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.habitbread.R
import com.example.habitbread.adapter.AlarmWeekAdapter
import com.example.habitbread.data.NewHabit
import com.example.habitbread.ui.viewModel.HabitViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_registraion.*
import org.koin.android.ext.android.get

class RegistrationBottomSheet : BottomSheetDialogFragment() {

    private lateinit var rvAlarmWeekDay: RecyclerView
    private lateinit var adapterAlarmWeekDay: AlarmWeekAdapter

    var getHabitTitle: String = ""
    var getHabitCategory: String = ""
    var getHabitAlarmDay: String = "1000000"
    var getHabitAlarmTime: String = ""

    private val habitViewModel: HabitViewModel = HabitViewModel.getInstance()

    override fun getTheme(): Int {
        return R.style.bottomSheetDialogTheme
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registraion, container, false)
        //rvAlarmWeekDay = view.findViewById(R.id.recyclerView_alarm)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //initRecyclerView()
        handlingCategoryChips()
        onClickCancle()
        imageView_close.setOnClickListener {
            dismiss()
        }
        onClickDone()
    }

    /*private fun initRecyclerView(){
        // alarm weekday recyclerView
        adapterAlarmWeekDay = AlarmWeekAdapter(context)
        rvAlarmWeekDay.adapter = adapterAlarmWeekDay
        rvAlarmWeekDay.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapterAlarmWeekDay.data = initWeekDayData
        adapterAlarmWeekDay.notifyDataSetChanged()
    }*/

    fun handlingCategoryChips(){
        chipGroup_category.setOnCheckedChangeListener { group, checkedId ->
            val chip: Chip? = view?.findViewById(checkedId)
            chip?.let {
                getHabitCategory = it.text.toString()
            }
        }
        chipGroup_alarm.setOnCheckedChangeListener { group, checkedId ->
            val chip: Chip? = view?.findViewById(checkedId)
            chip?.let {
                Toast.makeText(context, chip.text, Toast.LENGTH_SHORT).show()
                //convertDayToInt(chip.text.toString())
            }
        }
    }

//    fun convertDayToInt(day: String){
//        if(day == "월"){
//            getWeekDay = '1' + getWeekDay.substring(1)
//            Log.d("chohee", getWeekDay)
//        }else if(day == "화"){
//
//        }
//    }

//    private val initWeekDayData = listOf(
//        "월", "화", "수", "목", "금", "토", "일"
//    )

    fun onClickDone(){
        imageView_done.setOnClickListener {
            getHabitTitle = editText_title.text.toString()
            var data: NewHabit = NewHabit(title = getHabitTitle, category = getHabitCategory, dayOfWeek = getHabitAlarmDay, alarmTime = "")
            habitViewModel.postNewHabit(data)
            dismiss()
        }
    }

    fun onClickCancle(){
        imageView_close.setOnClickListener {
            dismiss()
        }
    }
}