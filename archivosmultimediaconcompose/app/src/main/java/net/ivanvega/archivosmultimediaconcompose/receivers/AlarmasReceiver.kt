package net.ivanvega.archivosmultimediaconcompose.receivers

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.ui.platform.LocalGraphicsContext
import androidx.core.app.NotificationCompat
import net.ivanvega.archivosmultimediaconcompose.R

class AlarmasReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.d("AlarmasReceiver", "Alarma recibida")
        val message = p1?.getStringExtra("EXTRA_MESSAGE") ?: return
        val channelId = "alarm_id"
        p0?.let { ctx ->
            val notificationManager =
                ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val builder = NotificationCompat.Builder(ctx, channelId)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Alarm Demo")
                .setContentText("Notification sent with message $message")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
            notificationManager.notify(1, builder.build())
        }
        Log.d("AlarmasReceiver", "Paso la creacion de la noti")

    }
}