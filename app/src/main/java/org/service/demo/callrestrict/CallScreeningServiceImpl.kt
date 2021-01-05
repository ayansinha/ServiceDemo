package org.service.demo.callrestrict

import android.content.Context
import android.content.Intent
import android.telecom.*
import android.util.Log
import android.view.View
import org.service.demo.callrestrict.Constant.incoming
import org.service.demo.util.toastLong

class CallScreeningServiceImpl : ConnectionService() {

    private lateinit var mView: View
    private lateinit var conn: Connection
    private lateinit var phoneNumber: String
    private lateinit var context: Context

    override fun onCreateIncomingConnection(
        connectionManagerPhoneAccount: PhoneAccountHandle?,
        request: ConnectionRequest?
    ): Connection {
        return super.onCreateIncomingConnection(connectionManagerPhoneAccount, request)
    }

    override fun onCreate() {
        this.context = this
        mView = View(this)
        super.onCreate()
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("TAG", "onStartCommand: ")
        phoneNumber = intent?.getStringExtra(incoming) ?: "number"
        toastLong("NHS-COVID-19-CALLER ID: $phoneNumber")

        //return START_STICKY
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d("TAG", "onDestroy: ")
        super.onDestroy()
    }
}