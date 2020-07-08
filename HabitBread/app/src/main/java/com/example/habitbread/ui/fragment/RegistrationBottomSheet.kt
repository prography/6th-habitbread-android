package com.example.habitbread.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.habitbread.R
import com.example.habitbread.adapter.AlarmWeekAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_registraion.*
import org.greenrobot.eventbus.EventBus

class RegistrationBottomSheet : BottomSheetDialogFragment() {

    private lateinit var rvAlarmWeekDay: RecyclerView
    private lateinit var adapterAlarmWeekDay: AlarmWeekAdapter

    var getHabitTitle: String = ""
    var getHabitCategory: String = "기타"
    var getHabitAlarmDay: String = "1000000"
    var getHabitAlarmTime: String = ""

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
        //handlingCategoryChips()
        onRegisterCancle()
        onRegisterDone()
    }

    /*private fun initRecyclerView(){
        // alarm weekday recyclerView
        adapterAlarmWeekDay = AlarmWeekAdapter(context)
        rvAlarmWeekDay.adapter = adapterAlarmWeekDay
        rvAlarmWeekDay.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapterAlarmWeekDay.data = initWeekDayData
        adapterAlarmWeekDay.notifyDataSetChanged()
    }*/

//    fun handlingCategoryChips(){
//        chipGroup_category.setOnCheckedChangeListener { group, checkedId ->
//            val chip: Chip? = view?.findViewById(checkedId)
//            chip?.let {
//                getHabitCategory = it.text.toString()
//            }
//        }
//        chipGroup_alarm.setOnCheckedChangeListener { group, checkedId ->
//            val chip: Chip? = view?.findViewById(checkedId)
//            chip?.let {
//                Toast.makeText(context, chip.text, Toast.LENGTH_SHORT).show()
//                //convertDayToInt(chip.text.toString())
//            }타
//        }
//    }

    fun onRegisterDone(){
        imageView_done.setOnClickListener {
            dismiss()
            getHabitTitle = editText_title.text.toString()
            if (getHabitTitle.isNotEmpty()) {
                EventBus.getDefault().post(ModalPost(getHabitTitle, getHabitCategory, getHabitAlarmDay, getHabitAlarmTime))
            } else {
                Toast.makeText(this.context, "제목이 있어야 합니다!!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    fun onRegisterCancle(){
        imageView_close.setOnClickListener {
            dismiss()
        }
    }
}