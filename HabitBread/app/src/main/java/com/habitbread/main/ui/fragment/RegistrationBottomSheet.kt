package com.habitbread.main.ui.fragment

import android.app.Dialog
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.habitbread.main.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_registraion.*
import org.greenrobot.eventbus.EventBus

class RegistrationBottomSheet : BottomSheetDialogFragment() {

    private val TAG: String = "RegistrationBottomSheet"
    var getHabitTitle: String = ""
    var getHabitCategory: String = "기타"
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
        onClickDayOfWeekChips()
        onCheckAlarmSwith()
        onRegisterCancle()
        onRegisterDone()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        bottomSheetDialog.setOnShowListener {dialog ->
            val bottomDialog = dialog as BottomSheetDialog
            val bottomSheet = bottomDialog.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
            val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet!!)
            bottomSheetBehavior.peekHeight = Resources.getSystem().displayMetrics.heightPixels
        }
        return bottomSheetDialog
    }

    fun onClickDayOfWeekChips(){
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

    fun onCheckAlarmSwith() {
        switch_alarm.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked == true) {
                timepicker_alarm_time.visibility = View.VISIBLE
                textView_isAlarmChecked.visibility = View.INVISIBLE
            }else {
                timepicker_alarm_time.visibility = View.INVISIBLE
                textView_isAlarmChecked.visibility = View.VISIBLE
            }
        }
    }

    fun onRegisterDone(){
        imageView_done.setOnClickListener {
            val getNewTitle = editText_title.text.toString()
            getHabitDescription = editText_description.text.toString()
            getHabitAlarmDay = ""
            for(i in 0..6) {
                getHabitAlarmDay += days[i]
            }
            if(switch_alarm.isChecked == true) {
                getHabitAlarmTime = timepicker_alarm_time.hour.toString() + ":" + timepicker_alarm_time.minute.toString()
            }else {
                getHabitAlarmTime = ""
            }

            // check all data is entered
            var isDoneReady: Boolean = true
            if(getNewTitle.length == 0) {
                isDoneReady = false
            }else {
                getHabitTitle = getNewTitle
            }
            if(getHabitAlarmDay == "0000000") {
                isDoneReady = false
            }

            // call create habit api
            if(isDoneReady == true) {
                EventBus.getDefault().post(ModalPost(getHabitTitle, getHabitCategory, getHabitDescription, getHabitAlarmDay, getHabitAlarmTime))
                findNavController().navigateUp()
            } else {
                Toast.makeText(context, "습관빵 이름과 빵 굽는 날은 반드시 입력해주세요!", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun onRegisterCancle(){
        imageView_close.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}