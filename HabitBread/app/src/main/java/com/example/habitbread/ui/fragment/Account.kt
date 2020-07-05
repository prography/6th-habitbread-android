package com.example.habitbread.ui.fragment

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
import com.example.habitbread.ui.viewModel.AccountViewModel
import com.example.habitbread.ui.viewModel.RankingViewModel
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
            progress_exp.progress = it.userPercentage
            textview_progress_exp.text = it.userPercentage.toString() + "%"
            textview_account_exp.text = it.userExp.toString()
            textview_bread_num.text = it.userLevel.toString()
            textview_current_bread_num.text = String.format(getString(R.string.currentBreadNum), it.userLevel);
        })
        setOnClickListener();
    }

    private fun setOnClickListener() {
        imageButton_ask.setOnClickListener {
            Log.d("HabitBread", "Nothing");
        }
        imageButton_change_nickname.setOnClickListener {
            Log.d("HabitBread", "ChangeNickname")
        }
        imageButton_delete_account.setOnClickListener {
            Log.d("HabitBread", "Delete Account")
        }
        imageButton_logout.setOnClickListener {
            Log.d("HabitBread", "Logout")
        }
        imageButton_ask.setOnClickListener {
            Log.d("HabitBread", "ask")
        }
        imageButton_change_info.setOnClickListener {
            Log.d("HabitBread", "Change Info")
        }
    }
}