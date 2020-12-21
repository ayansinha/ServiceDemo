package org.service.demo.callrestrict

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PixelFormat
import android.telecom.*
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import org.service.demo.R
import org.service.demo.util.toastLong

class CallScreeningServiceImpl: ConnectionService() {

    private lateinit var view: View
    private lateinit var conn: Connection
    private lateinit var phoneNumber: String

    /*@RequiresApi(Build.VERSION_CODES.Q)
    override fun onScreenCall(callDetails: Call.Details) {
        Log.d("TAG", "CallScreeningService: ")
        if ( callDetails.callDirection == Call.Details.DIRECTION_INCOMING) {
            val number = telNumber
        }
    }

    private fun releaseCall(details: Call.Details) {
        respondToCall(details, CallResponse.Builder().build())
    }*/


    override fun onCreateIncomingConnection(
        connectionManagerPhoneAccount: PhoneAccountHandle?,
        request: ConnectionRequest?
    ): Connection {
        Log.e("TAG", "CONNECTION onCreateIncomingConnection:" )

        /*conn.setCallerDisplayName("AYAN SINHA", PRESENTATION_ALLOWED)
        return conn*/
        return super.onCreateIncomingConnection(connectionManagerPhoneAccount, request)
    }

    override fun onCreate() {
        Log.e("TAG", "onCreate: ")
        super.onCreate()
    }

    override fun onStart(intent: Intent?, startId: Int) {
        Log.e("TAG", "onStart: ")
        super.onStart(intent, startId)
    }

    @SuppressLint("RtlHardcoded", "SetTextI18n", "InflateParams")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        /*CallConnection(this, intent).onShowIncomingCallUi()
        val user = conn.callerDisplayName*/
        phoneNumber = intent?.getStringExtra("incoming") ?: "number"
        toastLong("incoming... $phoneNumber" )
        Log.e("TAG", "onStartCommand:$phoneNumber" )

        //val textView = TextView(this)
        //val layout = findViewById<ConstraintLayout>(R.id.root)
        //setting height and width
        //textView.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        //layout?.addView(textView)
        val layoutParam = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            0,
            0,
            //WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
            0,
            PixelFormat.TRANSLUCENT
        )

        layoutParam.gravity = Gravity.CENTER
        //return super.onStartCommand(intent, flags, startId)
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = inflater.inflate(R.layout.incoming_view, null)
        val textView: TextView = view.findViewById(R.id.textViewIncoming)
        val phoneNumber = intent?.getStringExtra("incoming")
        textView.apply {
            text = "Calling ayan sinha"
            setBackgroundColor(Color.YELLOW)
        }
        return START_STICKY
    }

    override fun onDestroy() {
        Log.e("TAG", "onDestroy: ")
        val windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        //windowManager.removeView(view)
        super.onDestroy()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.e("TAG", "onUnbind: ")
        return super.onUnbind(intent)
    }

}

class CallConnection(private val context: Context, private val intent: Intent?): Connection() {
    override fun onCallAudioStateChanged(state: CallAudioState?) {
        Log.e("TAG", "CONNECTION onCallAudioStateChanged: ", )
    }

    override fun onShowIncomingCallUi() {
        Log.e("TAG", "CONNECTION onShowIncomingCallUi:.... " )
        //Toast.makeText(context, "cvcvcvcvcc", Toast.LENGTH_SHORT).show()
    }

    override fun onDisconnect() {
        Log.e("TAG", "CONNECTION onDisconnect: ", )
    }

    override fun onSeparate() {
        Log.e("TAG", "CONNECTION onSeparate: ", )
    }

    override fun onAbort() {
        Log.e("TAG", "CONNECTION onAbort: ", )
    }

    override fun onHold() {
        Log.e("TAG", "CONNECTION onHold: ", )
    }

    override fun onUnhold() {
        Log.e("TAG", "CONNECTION onUnhold: ", )
    }
    override fun onAnswer() {
        Log.e("TAG", "CONNECTION onReject: ", )
    }

    override fun onReject() {
        Log.e("TAG", "CONNECTION onReject: ", )
    }

    override fun onSilence() {
        Log.e("TAG", "CONNECTION onSilence: ", )
    }
}