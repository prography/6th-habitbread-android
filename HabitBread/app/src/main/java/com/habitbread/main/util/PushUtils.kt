package com.habitbread.main.util

import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId

class PushUtils {
    private fun getFCMCurrentToken(){
        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener(OnCompleteListener { task ->
            if(!task.isSuccessful){
                Log.w("FCMCurrentTokenTest", "getFCMCurrentToken failed", task.exception)
                return@OnCompleteListener
            }

            // This is a new Token(Instance ID)
            val token = task.result?.token.toString()
            Log.d("FCM_Token", token)
        })
    }
}