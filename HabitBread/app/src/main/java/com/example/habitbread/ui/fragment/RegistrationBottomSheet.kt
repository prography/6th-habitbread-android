package com.example.habitbread.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.habitbread.R
import com.example.habitbread.adapter.AlarmWeekAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_registraion.*

class RegistrationBottomSheet : BottomSheetDialogFragment() {

    private lateinit var rvAlarmWeekDay: RecyclerView
    private lateinit var adapterAlarmWeekDay: AlarmWeekAdapter
    var getCategory: String = ""
    var getWeekDay: String = "0000000"

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
                Toast.makeText(context, chip.text, Toast.LENGTH_SHORT).show()
                getCategory = chip.text.toString()
            }
        }
        Log.d("chohee", "들어옴")
        chipGroup_alarm.setOnCheckedChangeListener { group, checkedId ->
            val chip: Chip? = view?.findViewById(checkedId)
            Log.d("chohee", chip?.text.toString())
            chip?.let {
                Toast.makeText(context, chip.text, Toast.LENGTH_SHORT).show()
                //convertDayToInt(chip.text.toString())
            }
        }
    }

    fun convertDayToInt(day: String){
        if(day == "월"){
            getWeekDay = '1' + getWeekDay.substring(1)
            Log.d("chohee", getWeekDay)
        }else if(day == "화"){

        }
    }

    private val initWeekDayData = listOf(
        "월", "화", "수", "목", "금", "토", "일"
    )

    fun onClickDone(){
        imageView_done.setOnClickListener {
            var getTitle = editText_title.text

        }
    }
}