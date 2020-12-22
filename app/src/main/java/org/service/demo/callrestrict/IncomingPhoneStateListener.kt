package org.service.demo.callrestrict

import android.content.Context
import android.content.Intent
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import android.util.Log
import org.service.demo.callrestrict.Number.incoming
import org.service.demo.util.toastLong

class IncomingPhoneStateListener(private var context: Context): PhoneStateListener() {
    override fun onCallStateChanged(state: Int, phoneNumber: String?) {
        super.onCallStateChanged(state, phoneNumber)
        //context.toastLong("NHS-COVID-19-CALLER ID : $phoneNumber")
        Log.e("TAG", "onCallStateChanged: ")
        when (state) {
            TelephonyManager.CALL_STATE_RINGING -> {
                Log.e("TAG", "RINGING onCallStateChanged: $phoneNumber")
                val intent = Intent(context, CallScreeningServiceImpl::class.java)
                intent.putExtra(incoming, phoneNumber)
                context.startService(intent)
            }
            TelephonyManager.CALL_STATE_IDLE -> {
                Log.e("TAG", "IDLE onCallStateChanged: $phoneNumber")
                //context.toastLong("NHS-COVID-19-CALLER ID : $phoneNumber")
            }
            TelephonyManager.CALL_STATE_OFFHOOK -> {
                Log.e("TAG", "OFFHOOK onCallStateChanged: $phoneNumber")
                context.toastLong("NHS-COVID-19-CALLER ID : $phoneNumber")
            }
        }
    }
}
