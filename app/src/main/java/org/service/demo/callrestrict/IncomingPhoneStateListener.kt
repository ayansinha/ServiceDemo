package org.service.demo.callrestrict

import android.content.Context
import android.content.Intent
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import android.util.Log
import org.service.demo.callrestrict.Constant.incoming
import org.service.demo.util.toastLong

class IncomingPhoneStateListener(private var context: Context): PhoneStateListener() {
    override fun onCallStateChanged(state: Int, phoneNumber: String?) {
        super.onCallStateChanged(state, phoneNumber)
        when (state) {
            TelephonyManager.CALL_STATE_RINGING -> {
                val intent = Intent(context, CallScreeningServiceImpl::class.java)
                intent.putExtra(incoming, phoneNumber)
                context.startService(intent)
            }
            TelephonyManager.CALL_STATE_OFFHOOK -> {
                context.toastLong("NHS-COVID-19-CALLER ID : $phoneNumber")
            }
        }
    }
}
