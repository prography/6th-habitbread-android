package com.example.habitbread.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.habitbread.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_registraion.*
import org.greenrobot.eventbus.EventBus

class RegistrationBottomSheet : BottomSheetDialogFragment() {

    var getHabitTitle: String = ""
    var getHabitCategory: String = "운동"
    var getHabitDescription: String? = ""
    var getHabitAlarmDay: String = ""
    var getHabitAlarmTime: String = ""
    var days: MutableList<String> = mutableListOf("0", "0", "0", "0", "0", "0", "0")

    override fun getTheme(): Int {
        return R.style.bottomSheetDialogTheme
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registraion, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        handlingCategoryChips()
        onRegisterCancle()
        onRegisterDone()
    }

    fun handlingCategoryChips(){
        chip_mon.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked == true) {
                buttonView.setTextColor(Color.parseColor("#FFFFFF"))
                days[1] = "1"
            }else {
                buttonView.setTextColor(Color.parseColor("#787877"))
                days[1] = "0"
            }
        }
        chip_tue.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked == true) {
                buttonView.setTextColor(Color.parseColor("#FFFFFF"))
                days[2] = "1"
            }else {
                buttonView.setTextColor(Color.parseColor("#787877"))
                days[2] = "0"
            }
        }
        chip_wed.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked == true) {
                buttonView.setTextColor(Color.parseColor("#FFFFFF"))
                days[3] = "1"
            }else {
                buttonView.setTextColor(Color.parseColor("#787877"))
                days[3] = "0"
            }
        }
        chip_thu.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked == true) {
                buttonView.setTextColor(Color.parseColor("#FFFFFF"))
                days[4] = "1"
            }else {
                buttonView.setTextColor(Color.parseColor("#787877"))
                days[4] = "0"
            }
        }
        chip_fri.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked == true) {
                buttonView.setTextColor(Color.parseColor("#FFFFFF"))
                days[5] = "1"
            }else {
                buttonView.setTextColor(Color.parseColor("#787877"))
                days[5] = "0"
            }
        }
        chip_sat.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked == true) {
                buttonView.setTextColor(Color.parseColor("#FFFFFF"))
                days[6] = "1"
            }else {
                buttonView.setTextColor(Color.parseColor("#787877"))
                days[6] = "0"
            }
        }
        chip_sun.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked == true) {
                buttonView.setTextColor(Color.parseColor("#FFFFFF"))
                days[0] = "1"
            }else {
                buttonView.setTextColor(Color.parseColor("#787877"))
                days[0] = "0"
            }
        }
    }

    fun onRegisterDone(){
        imageView_done.setOnClickListener {
            getHabitTitle = editText_title.text.toString()
            getHabitDescription = editText_description.text.toString()
            for(i in 0..6) {
                getHabitAlarmDay += days[i]
            }
            getHabitAlarmTime = timepicker_alarm_time.hour.toString() + ":" + timepicker_alarm_time.minute.toString()
            EventBus.getDefault().post(ModalPost(getHabitTitle, getHabitCategory, getHabitDescription, getHabitAlarmDay, getHabitAlarmTime))
            dismiss()
        }
    }

    fun onRegisterCancle(){
        imageView_close.setOnClickListener {
            dismiss()
        }
    }
}