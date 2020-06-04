package com.example.habitbread.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.habitbread.R
import com.example.habitbread.adapter.AlarmWeekAdapter
import com.example.habitbread.adapter.CategoryAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.ChipGroup
import kotlinx.android.synthetic.main.fragment_registraion.*

class RegistrationBottomSheet : BottomSheetDialogFragment() {

    private lateinit var rvCategory: RecyclerView
    private lateinit var adapterCategory: CategoryAdapter

    private lateinit var rvAlarmWeekDay: RecyclerView
    private lateinit var adapterAlarmWeekDay: AlarmWeekAdapter

    private lateinit var chipGroupCategory: ChipGroup

    override fun getTheme(): Int {
        return R.style.bottomSheetDialogTheme
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registraion, container, false)
        chipGroupCategory = view.findViewById(R.id.chipGroup_category)
//        rvCategory = view.findViewById(R.id.recyclerView_category)
//        rvAlarmWeekDay = view.findViewById(R.id.recyclerView_alarm)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //initRecyclerView()
        handlingCategoryChips()
        imageView_close.setOnClickListener {
            dismiss()
        }
    }

//    private fun initRecyclerView(){
//        // category recyclerView
//        adapterCategory = CategoryAdapter(context)
//        rvCategory.adapter = adapterCategory
//        rvCategory.layoutManager = GridLayoutManager(context, 3)
//        adapterCategory.data = initCategoryData
//        adapterCategory.notifyDataSetChanged()
//
//        // alarm weekday recyclerView
//        adapterAlarmWeekDay = AlarmWeekAdapter(context)
//        rvAlarmWeekDay.adapter = adapterAlarmWeekDay
//        rvAlarmWeekDay.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        adapterAlarmWeekDay.data = initWeekDayData
//        adapterAlarmWeekDay.notifyDataSetChanged()
//    }

    fun handlingCategoryChips(){
        chipGroupCategory.setOnCheckedChangeListener { group, checkedId ->
        }
    }

    private val initCategoryData = listOf(
        "건강", "공부", "운동", "생활", "취미", "기타"
    )

    private val initWeekDayData = listOf(
        "월", "화", "수", "목", "금", "토", "일"
    )
}