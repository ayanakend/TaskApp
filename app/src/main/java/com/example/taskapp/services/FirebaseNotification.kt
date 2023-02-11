package com.example.taskapp.services

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.taskapp.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class FirebaseNotification : FirebaseMessagingService() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(message: RemoteMessage) {
        Log.e("ololo", "onMessageReceived: "+message.notification?.title )
        Log.e("ololo", "onMessageReceived: "+message.notification?.body )
        sendNotifications(message)
        super.onMessageReceived(message)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun sendNotifications(message: RemoteMessage) {
        val notificationBuilder = NotificationCompat.Builder(this, "task_channelId")
            .setSmallIcon(R.mipmap.icon)
            .setContentTitle(message.notification?.title)
            .setContentText(message.notification?.body)

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            "task_channelId",
            "Heads up Notification",
            NotificationManager.IMPORTANCE_DEFAULT
        )

        notificationManager.createNotificationChannel(channel)
        notificationManager.notify(1, notificationBuilder.build())
    }
}