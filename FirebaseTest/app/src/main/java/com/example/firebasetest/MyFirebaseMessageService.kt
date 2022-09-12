package com.example.firebasetest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessageService : FirebaseMessagingService() {
    companion object{
        private const val TAG = "FCM TOKEN"
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        // 토큰 갱신 시 호출 -> 서버로 업데이트 된 토큰 보내기
        Log.d(TAG, "FCM TOKEN CREATED : $token")
        // sendTokenToServer(token)
    }

    // 현재 토큰 가져오기
    fun getToken() : String? {
        var token: String? = null
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful){
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            token = task.result
            Log.d(TAG, "FCM Token : $token")
        })
        return token
    }

    override fun onMessageReceived(message: RemoteMessage) {
        // 메시지가 수신 되는 곳
        super.onMessageReceived(message)
        val from = message.from
        val title = message.notification?.title
        val body = message.notification?.body

        /*
            // 데이터 페이로드가 포함된 경우
            val requestId = Integer.parseInt(message.data["requestId"])
         */
        Log.d(TAG, "message received : $message")
        Log.d(TAG, "from : $from title : $title body : $body")
        sendNotification(title!!, body!!)
    }


    private fun sendNotification(title: String, text: String){
        // 알림 클릭시 해당 액티비티로 오게
        val resultIntent = Intent(this, TestActivity::class.java)
        // 백 스택 만들기
        val resultPendingIntent : PendingIntent? = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(resultIntent)
            getPendingIntent(0, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val channelId = getString(R.string.default_notification_channel_id)
        val channelName = getString(R.string.default_notification_channel_name)
        val defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setSound(defaultSound)
            .setContentText(text)
            .setContentTitle(title)
            .setContentIntent(resultPendingIntent)
        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
        with(NotificationManagerCompat.from(this)){
            createNotificationChannel(channel)
            notify(0, notificationBuilder.build())
        }
//        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        notificationManager.createNotificationChannel(channel)
//        notificationManager.notify(0, notificationBuilder.build())
    }
}