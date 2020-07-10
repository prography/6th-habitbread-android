package com.example.habitbread.ui.fragment

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.DialogCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.habitbread.R
import com.example.habitbread.base.BaseApplication
import com.example.habitbread.ui.activity.LoginActivity
import com.example.habitbread.ui.activity.MainActivity
import com.example.habitbread.ui.viewModel.AccountViewModel
import com.example.habitbread.util.AccountUtils
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
        imageButton_change_nickname.setOnClickListener {
            showNotReadyToast()
        }
        imageButton_delete_account.setOnClickListener {
            deleteAccount()
        }
        imageButton_logout.setOnClickListener {
            signOut()
        }
        imageButton_change_info.setOnClickListener {
            showNotReadyToast()
        }
    }

    private fun showNotReadyToast() {
        Toast.makeText(this.context, "아직 준비중인 기능입니다.", Toast.LENGTH_SHORT).show();
    }

    private fun deleteAccount() {
        val dialog = AlertDialog.Builder(requireContext())
            .setMessage("정말 탈퇴 하시겠습니까??")
            .setPositiveButton("네") { dialogInterface: DialogInterface, i: Int ->
                BaseApplication.preferences.clearPreferences()
                accountViewModel.deleteAccount()
                AccountUtils(this.requireContext()).revokeAccess().addOnCompleteListener {
                    backToLogin()
                }
            }.setNegativeButton("아니요") { dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()
            };
        dialog.create().show();
    }

    private fun signOut() {
        val dialog = AlertDialog.Builder(requireContext())
            .setMessage("정말 로그아웃 하시겠습니까??")
            .setPositiveButton("네") { dialogInterface: DialogInterface, i: Int ->
                BaseApplication.preferences.clearPreferences()
                AccountUtils(this.requireContext()).signOut().addOnCompleteListener {
                    backToLogin()
                }
            }.setNegativeButton("아니요") { dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()
            };
        dialog.create().show();
    }

    private fun backToLogin() {
        val intent = Intent(this.requireActivity(), LoginActivity::class.java);
        startActivity(intent);
        this.requireActivity().finish();
    }
}