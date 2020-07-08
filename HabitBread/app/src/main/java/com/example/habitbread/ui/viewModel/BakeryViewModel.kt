package com.example.habitbread.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.habitbread.base.BaseViewModel
import com.example.habitbread.data.BreadResponse
import com.example.habitbread.repository.BakeryRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BakeryViewModel: BaseViewModel(){
    var breadsData: MutableLiveData<List<BreadResponse>?> = MutableLiveData()

    fun getBreads(){
        GlobalScope.launch {
            try {
                val data = BakeryRepository().getBreads()
                breadsData.postValue(data)
            }catch (err: Error){
                Log.e("HabitBread", err.printStackTrace().toString())
            }
        }
    }
}