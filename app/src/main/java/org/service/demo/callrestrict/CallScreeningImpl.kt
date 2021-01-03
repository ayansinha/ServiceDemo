package org.service.demo.callrestrict

import android.content.Intent
import android.os.IBinder
import android.telecom.Call
import android.telecom.CallScreeningService

class CallScreeningImpl: CallScreeningService() {
    override fun onScreenCall(calDetails: Call.Details) {

    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return super.onBind(intent)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }
}