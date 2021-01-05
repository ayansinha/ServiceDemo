package org.service.demo.callrestrict

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import android.util.Log
import androidx.annotation.UiThread

class CallScreenReceiver : BroadcastReceiver() {

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context, intent: Intent) {
        Log.d("TAG", "onReceive: ")

        val telephonyManager = context.getSystemService(TelephonyManager::class.java)
        telephonyManager?.listen(
            IncomingPhoneStateListener(context),
            PhoneStateListener.LISTEN_CALL_STATE
        )
    }
}
