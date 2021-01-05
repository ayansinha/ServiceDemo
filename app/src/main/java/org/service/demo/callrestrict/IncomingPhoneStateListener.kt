package org.service.demo.callrestrict

import android.content.Context
import android.content.Intent
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import android.util.Log
import org.service.demo.callrestrict.Constant.incoming
import org.service.demo.callrestrict.Constant.telNumber
import org.service.demo.util.toastLong

class IncomingPhoneStateListener(private var context: Context): PhoneStateListener() {
    override fun onCallStateChanged(state: Int, phoneNumber: String?) {
        super.onCallStateChanged(state, phoneNumber)
        when (state) {
            TelephonyManager.CALL_STATE_RINGING -> {
                Log.d("TAG", "onCallStateChanged: RINGING")
                if (telNumber == phoneNumber) {
                    //context.toastLong("NUMBER MATCHED : $phoneNumber")
                    Log.d("TAG", "NUMBER MATCHED: $phoneNumber")
                    val intent = Intent(context, CallScreeningServiceImpl::class.java)
                    intent.putExtra(incoming, phoneNumber)
                    context.startService(intent)
                } else {
                    //context.toastLong("NUMBER NOT MATCHED : $phoneNumber")
                    Log.d("TAG", "NUMBER NOT MATCHED: $phoneNumber")
                }
            }
            TelephonyManager.CALL_STATE_OFFHOOK -> {
                Log.d("TAG", "onCallStateChanged: OFF_HOOK")
                //context.toastLong("NHS-COVID-19-CALLER ID : $phoneNumber")
            }
            TelephonyManager.CALL_STATE_IDLE -> {
                Log.d("TAG", "onCallStateChanged: IDLE")
            }
        }
    }
}
