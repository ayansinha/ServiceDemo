package org.service.demo.callrestrict

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PixelFormat
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import org.service.demo.R
import org.service.demo.util.toastLong

class CallScreenReceiver : BroadcastReceiver() {
    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context, intent: Intent) {
        val telephonyManager = context.getSystemService(TelephonyManager::class.java)
        telephonyManager?.listen(
            IncomingPhoneStateListener(context),
            PhoneStateListener.LISTEN_CALL_STATE
        )
    }
}
