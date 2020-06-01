package com.example.habitbread.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.habitbread.R
import com.example.habitbread.adapter.CategoryAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_registraion.*

class RegistrationBottomSheet : BottomSheetDialogFragment() {

    private lateinit var rvCategory: RecyclerView
    private lateinit var adapterCategory: CategoryAdapter

    override fun getTheme(): Int {
        return R.style.bottomSheetDialogTheme
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registraion, container, false)
        rvCategory = view!!.findViewById(R.id.recyclerView_category)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRvCategory()
        imageView_close.setOnClickListener {
            dismiss()
        }
    }

    private fun initRvCategory(){
        adapterCategory = CategoryAdapter(context)
        rvCategory.adapter = adapterCategory
        rvCategory.layoutManager = GridLayoutManager(context, 3)
        adapterCategory.data = initCategoryData
        adapterCategory.notifyDataSetChanged()
    }

    private val initCategoryData = listOf(
        "건강", "공부", "운동", "생활", "취미", "기타"
    )
}