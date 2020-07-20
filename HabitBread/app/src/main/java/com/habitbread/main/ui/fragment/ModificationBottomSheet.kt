package com.habitbread.main.ui.fragment

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.habitbread.main.R
import com.habitbread.main.ui.viewModel.DetailViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.switchmaterial.SwitchMaterial
import com.habitbread.main.base.BaseApplication
import com.habitbread.main.util.AccountUtils
import kotlinx.android.synthetic.main.fragment_modification.*
import ru.ifr0z.timepickercompact.TimePickerCompact

class ModificationBottomSheet : BottomSheetDialogFragment() {

    private val TAG: String = "ModificationBottomSheet"
    var getHabitId: Int = -1
    var getHabitTitle: String = ""
    var getHabitCategory: String = "기타"
    var getHabitDescription: String? = ""
    var getHabitAlarmDay: String = ""
    var getHabitAlarmTime: String? = ""
    private lateinit var timepicker_alarm_time: TimePickerCompact
    private lateinit var editText_title: EditText
    private lateinit var editText_description: EditText
    private lateinit var textView_isAlarmChecked: TextView
    private lateinit var switch_alarm: SwitchMaterial
    private lateinit var setNewDataOnHabitListener: SetNewDataOnHabitListener
    val detailViewModel: DetailViewModel by viewModels()

    override fun getTheme(): Int {
        return R.style.bottomSheetDialogTheme
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_modification, container, false)
        timepicker_alarm_time = view.findViewById(R.id.timepicker_alarm_time)
        editText_title = view.findViewById(R.id.editText_title)
        editText_description = view.findViewById(R.id.editText_description)
        textView_isAlarmChecked = view.findViewById(R.id.textView_isAlarmChecked)
        switch_alarm = view.findViewById(R.id.switch_alarm)
        setPreviousDetailData(view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        onClickDayOfWeekChips()
        onCheckAlarmSwith()
        onModifyCancle()
        onModifyDone()
        onClickDelete()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            setNewDataOnHabitListener = context as SetNewDataOnHabitListener
        }catch (e: ClassCastException) {
            Log.e(TAG, "onAttach Error")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog;
        bottomSheetDialog.setOnShowListener {dialog ->
            val bottomDialog = dialog as BottomSheetDialog
            val bottomSheet = bottomDialog.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
            val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet!!)
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED;
        }
        return bottomSheetDialog
    }

    private fun setPreviousDetailData(view: View) {
        // get previous detail datas from activity
        getHabitId = this.requireArguments().getInt("habitId")
        getHabitTitle = this.requireArguments().getString("title").toString()
        getHabitDescription = this.arguments?.getString("description")
        getHabitAlarmDay = this.requireArguments().getString("dayOfWeek").toString()
        getHabitAlarmTime = this.requireArguments().getString("alarmTime")

        // set previous detail data
        editText_title.hint = getHabitTitle
        editText_title.setHintTextColor(Color.parseColor("#000000"))
        if(getHabitDescription == null) {
            editText_description.hint = "내용을 적어주세요"
        }else {
            editText_description.hint = getHabitDescription
            editText_description.setHintTextColor(Color.parseColor("#000000"))
        }
        for(i in 0..6) {
            if(getHabitAlarmDay?.get(i) == '1') {
                view.findViewWithTag<Chip>(i.toString()).isCheckable = true
                view.findViewWithTag<Chip>(i.toString()).isChecked = true
                view.findViewWithTag<Chip>(i.toString()).setTextColor(Color.parseColor("#FFFFFF"))
                view.findViewWithTag<Chip>(i.toString()).isCheckable = false
            }
        }
        if(getHabitAlarmTime != null) {
            val previousAlarmHour: Int = getHabitAlarmTime!!.substring(0, 2).toInt()
            val previousAlarmMinute: Int = getHabitAlarmTime!!.substring(3, 5).toInt()
            timepicker_alarm_time.hour = previousAlarmHour
            timepicker_alarm_time.minute = previousAlarmMinute
        }else {
            timepicker_alarm_time.visibility = View.INVISIBLE
            textView_isAlarmChecked.visibility = View.VISIBLE
            switch_alarm.isChecked = false
        }
    }

    fun onClickDayOfWeekChips(){
        chip_mon.setOnClickListener {
            Toast.makeText(context, "죄송합니다. 요일은 수정할 수 없습니다.", Toast.LENGTH_SHORT).show()
        }
        chip_tue.setOnClickListener {
            Toast.makeText(context, "죄송합니다. 요일은 수정할 수 없습니다.", Toast.LENGTH_SHORT).show()
        }
        chip_wed.setOnClickListener {
            Toast.makeText(context, "죄송합니다. 요일은 수정할 수 없습니다.", Toast.LENGTH_SHORT).show()
        }
        chip_thu.setOnClickListener {
            Toast.makeText(context, "죄송합니다. 요일은 수정할 수 없습니다.", Toast.LENGTH_SHORT).show()
        }
        chip_fri.setOnClickListener {
            Toast.makeText(context, "죄송합니다. 요일은 수정할 수 없습니다.", Toast.LENGTH_SHORT).show()
        }
        chip_sat.setOnClickListener {
            Toast.makeText(context, "죄송합니다. 요일은 수정할 수 없습니다.", Toast.LENGTH_SHORT).show()
        }
        chip_sun.setOnClickListener {
            Toast.makeText(context, "죄송합니다. 요일은 수정할 수 없습니다.", Toast.LENGTH_SHORT).show()
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

    fun onModifyDone(){
        imageView_done.setOnClickListener {
            val getChangedTitle = editText_title.text.toString()
            val getChangedDescription = editText_description.text.toString()

            // check all is entered
            if(getChangedTitle.length != 0) {
                getHabitTitle = getChangedTitle
            }
            if(getChangedDescription.length != 0) {
                getHabitDescription = getChangedDescription
            }
            if(switch_alarm.isChecked == true) {
                getHabitAlarmTime = timepicker_alarm_time.hour.toString() + ":" + timepicker_alarm_time.minute.toString()
            }else {
                getHabitAlarmTime = ""
            }

            setNewDataOnHabitListener.setNewDataOnHabit(getHabitTitle, getHabitCategory, getHabitDescription, getHabitAlarmTime)
            dismiss()
        }
    }

    interface SetNewDataOnHabitListener {
        fun setNewDataOnHabit(newTitle: String, newCategory: String, newDescription: String?, newAlarmTime: String?)
    }

    fun onModifyCancle(){
        imageView_close.setOnClickListener {
            dismiss()
        }
    }

//    private fun onClickDelete() {
//        textView_modify_delete.setOnClickListener {
//            detailViewModel.deleteHabit(getHabitId)
//            detailViewModel.deleteData.observe(this, Observer {
//                if(it.message == "success") {
//                    activity?.finish()
//                    Toast.makeText(context, "습관빵이 삭제되었습니다.", Toast.LENGTH_SHORT).show()
//                }else {
//                    Toast.makeText(context, "죄송합니다. 오류로 인해 습관빵이 삭제되지 않았습니다.", Toast.LENGTH_SHORT).show()
//                }
//            })
//        }
//    }

    private fun onClickDelete() {
        textView_modify_delete.setOnClickListener {
            val dialog = AlertDialog.Builder(requireContext())
                .setMessage("정말 삭제 하시겠습니까?")
                .setPositiveButton("예") { dialogInterface: DialogInterface, i: Int ->
                    detailViewModel.deleteHabit(getHabitId)
                    detailViewModel.deleteData.observe(this, Observer {
                        if(it.message == "success") {
                            activity?.finish()
                            Toast.makeText(context, "습관빵이 삭제되었습니다.", Toast.LENGTH_SHORT).show()
                        }else {
                            Toast.makeText(context, "죄송합니다. 오류로 인해 습관빵이 삭제되지 않았습니다.", Toast.LENGTH_SHORT).show()
                        }
                    })
                }.setNegativeButton("아니요") { dialogInterface: DialogInterface, i: Int ->
                    dialogInterface.dismiss()
                }
            dialog.create().show()
        }
    }
}