package com.example.habitbread.ui.fragment

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.habitbread.R
import com.example.habitbread.base.BaseApplication
import com.example.habitbread.ui.activity.MainActivity
import com.example.habitbread.ui.viewModel.AccountViewModel
import kotlinx.android.synthetic.main.fragment_account.*

class Account : Fragment() {
    val accountViewModel : AccountViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_account, container, false);
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        accountViewModel.getUserInfo();
        accountViewModel.accountData.observe(viewLifecycleOwner, Observer {
            textview_profile_nickname.text = it.accountName
            progress_exp.progress = it.percent
            textview_progress_exp.text = it.percent.toString() + "%"
            textview_account_exp.text = it.userExp.toString()
            textview_bread_num.text = it.totalItemCount.toString()
            textview_current_bread_num.text = String.format(getString(R.string.currentBreadNum), it.totalItemCount);
        })
        setOnClickListener();
        setOnToggleListener();
    }

    private fun setOnToggleListener() {
        switch_alarm.setOnCheckedChangeListener { buttonView, isChecked ->
            // todo : add fcm to check it. ( register & unregister ) 
        }
    }

    private fun setOnClickListener() {
        imageButton_ask.setOnClickListener {
            showNotReadyToast();
        }
        imageButton_change_nickname.setOnClickListener {
            Log.d("HabitBread", "ChangeNickname")
        }
        imageButton_delete_account.setOnClickListener {
            deleteAccount()
        }
        imageButton_logout.setOnClickListener {
            signOut()
        }
        imageButton_ask.setOnClickListener {
            showNotReadyToast()

        }
        imageButton_change_info.setOnClickListener {
            showNotReadyToast()
        }
    }

    private fun showNotReadyToast() {
        Toast.makeText(this.context, "아직 준비중인 기능입니다.", Toast.LENGTH_SHORT).show();
    }

    private fun deleteAccount() {
    }

    private fun signOut() {
        //todo : signout with sharedPreferences and go to LoginActivity signOut
    }
}