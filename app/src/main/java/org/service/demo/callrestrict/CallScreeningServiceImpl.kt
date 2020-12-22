package org.service.demo.callrestrict

import android.annotation.SuppressLint
import android.app.UiModeManager
import android.app.role.RoleManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.telecom.*
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import org.service.demo.R
import org.service.demo.callrestrict.Number.incoming
import org.service.demo.util.showSnackBar
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
        Log.e("TAG", "CONNECTION onCreateIncomingConnection:")
        return super.onCreateIncomingConnection(connectionManagerPhoneAccount, request)
    }

    override fun onCreate() {
        Log.e("TAG", "onCreate: ")
        this.context = this
        mView = View(this)
        super.onCreate()
    }

    override fun onStart(intent: Intent?, startId: Int) {
        Log.e("TAG", "onStart: ")
        super.onStart(intent, startId)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        phoneNumber = intent?.getStringExtra(incoming) ?: "number"
        toastLong("NHS-COVID-19-CALLER ID: $phoneNumber")
        //mView.showSnackBar("NHS-COVID-19-CALLER ID: $phoneNumber")
        Log.e("TAG", "onStartCommand:$phoneNumber")

        /*val layoutParam = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
            *//*0,
            0,
            //WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
            0,
            PixelFormat.TRANSLUCENT*//*
        )

        var mView = View(context)
        layoutParam.gravity = Gravity.CENTER
        //return super.onStartCommand(intent, flags, startId)
        val inflater: LayoutInflater =
            getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mView = inflater.inflate(R.layout.incoming_view, null)
        val textView: TextView = mView.findViewById(R.id.textViewIncoming)
        val phoneNumber = intent?.getStringExtra("incoming")
        textView.text = "Incoming call \n $phoneNumber"
        textView.setBackgroundColor(Color.YELLOW)
        *//*textView.apply {
            text = "Calling ayan sinha"
            setBackgroundColor(Color.YELLOW)
        }*/
        return START_STICKY
    }

    override fun onDestroy() {
        Log.e("TAG", "onDestroy: ")
        super.onDestroy()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.e("TAG", "onUnbind: ")
        return super.onUnbind(intent)
    }

}

class CallConnection(private val context: Context, private val intent: Intent?) : Connection() {
    override fun onCallAudioStateChanged(state: CallAudioState?) {
        Log.e("TAG", "CONNECTION onCallAudioStateChanged: ")
    }

    @SuppressLint("InflateParams")
    override fun onShowIncomingCallUi() {
        Log.e("TAG", "CONNECTION onShowIncomingCallUi:.... ")
        //Toast.makeText(context, "cvcvcvcvcc", Toast.LENGTH_SHORT).show()
    }

    override fun onDisconnect() {
        Log.e("TAG", "CONNECTION onDisconnect: ")
    }

    override fun onSeparate() {
        Log.e("TAG", "CONNECTION onSeparate: ")
    }

    override fun onAbort() {
        Log.e("TAG", "CONNECTION onAbort: ")
    }

    override fun onHold() {
        Log.e("TAG", "CONNECTION onHold: ")
    }

    override fun onUnhold() {
        Log.e("TAG", "CONNECTION onUnhold: ")
    }

    override fun onAnswer() {
        Log.e("TAG", "CONNECTION onAnswer: ")
    }

    override fun onReject() {
        Log.e("TAG", "CONNECTION onReject: ")
    }

    override fun onSilence() {
        Log.e("TAG", "CONNECTION onSilence: ")
    }
}