package com.demo.testnotifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showNotificationButton.setOnClickListener {
            showNotification()
        }
    }

    private fun showNotification() {
        val channelID = "10000"
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val contentView = RemoteViews(packageName, R.layout.custom_notification_layout)
        contentView.setTextViewText(R.id.tvTitle, "My Custom Notification Title")
        contentView.setTextViewText(R.id.tvDesc, "My Custom Notification Body")

        val builder = NotificationCompat.Builder(applicationContext,channelID)
                .setSmallIcon(R.drawable.notifications_black)
               // .setContentTitle("My Notification Title")
              //  .setContentText("My Notification Body")
                .setCustomContentView(contentView)

        builder.setContent(contentView)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelID, "Custom Notification", NotificationManager.IMPORTANCE_DEFAULT)

            channel.enableVibration(true)
            notificationManager.createNotificationChannel(channel)
            builder.setChannelId(channelID)
        }
        val notification = builder.build()

        notificationManager.notify(1000, notification)
    }
}