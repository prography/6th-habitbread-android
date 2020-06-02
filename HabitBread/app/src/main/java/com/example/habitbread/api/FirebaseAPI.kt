package com.example.habitbread.api

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService

class FirebaseAPI : FirebaseMessagingService() {

    // 새 토큰이 생성될 때마다 onNewToken 콜백이 호출됨
    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")
        super.onNewToken(token)
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        // 새로운 토큰을 사용하기 위해 sendRegistrationToServer(token) 같은 함수 호출하기
    }
}