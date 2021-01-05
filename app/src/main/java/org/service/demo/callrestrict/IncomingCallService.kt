package org.service.demo.callrestrict

import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import android.telecom.Call
import android.telecom.CallAudioState
import android.telecom.InCallService
import android.util.Log

class IncomingCallService : InCallService() {
    override fun onBind(intent: Intent?): IBinder? {
        return super.onBind(intent)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    override fun onCallAudioStateChanged(audioState: CallAudioState?) {
        super.onCallAudioStateChanged(audioState)
    }

    override fun onBringToForeground(showDialpad: Boolean) {
        super.onBringToForeground(showDialpad)
    }

    override fun onCallAdded(call: Call?) {
        Log.d("TAG", "Callback onCallAdded: $call")
        call?.registerCallback(callBack)
        super.onCallAdded(call)
    }

    override fun onCallRemoved(call: Call?) {
        Log.d("TAG", "Callback onCallRemoved: $call")
        call?.unregisterCallback(callBack)
        super.onCallRemoved(call)
    }

    override fun onCanAddCallChanged(canAddCall: Boolean) {
        super.onCanAddCallChanged(canAddCall)
    }

    override fun onSilenceRinger() {
        super.onSilenceRinger()
    }

    override fun onConnectionEvent(call: Call?, event: String?, extras: Bundle?) {
        super.onConnectionEvent(call, event, extras)
    }

    private val callBack = object: Call.Callback() {
        override fun onStateChanged(call: Call?, state: Int) {
            Log.d("TAG", "Callback onStateChanged: $call, state: $state")
            super.onStateChanged(call, state)
        }
    }
}