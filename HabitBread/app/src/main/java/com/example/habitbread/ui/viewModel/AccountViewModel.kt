package com.example.habitbread.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.habitbread.data.AccountResponse
import com.example.habitbread.data.BaseResponse
import com.example.habitbread.repository.AccountRepository
import com.example.habitbread.repository.RankingRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Exception

class AccountViewModel : ViewModel() {

    val accountData: MutableLiveData<AccountResponse> = MutableLiveData()

    fun getUserInfo() {
        GlobalScope.launch {
            try {
                val accountList = AccountRepository().getUserInfo()
                accountData.postValue(accountList)
            } catch (e : Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteAccount() {
       GlobalScope.launch {
           try {
               val response = AccountRepository().deleteAccount();
               Log.d("HabitBread", response.toString())
           } catch (e : Exception) {
               e.printStackTrace()
           }
       }
    }
}