package com.habitbread.main.util

import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.runBlocking
import java.lang.Exception

class PushUtils {

    fun register() {
        getFCMCurrentToken()
        FirebaseMessaging.getInstance().isAutoInitEnabled = true
    }

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

    fun unregister() {
        FirebaseMessaging.getInstance().isAutoInitEnabled = false
        Thread {
            try {
                FirebaseInstanceId.getInstance().deleteInstanceId()
            } catch (e : Exception) {
                e.printStackTrace()
            }
        }
    }
}