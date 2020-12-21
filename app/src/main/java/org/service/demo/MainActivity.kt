package org.service.demo

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import org.service.demo.util.toastLong


class MainActivity : AppCompatActivity() {

    private val PERMISSION_REQUEST_CODE = 200

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val tv: TextView = findViewById(R.id.textView)
        val buttonSchedule: Button = findViewById(R.id.buttonSchedule)
        val buttonCancel: Button = findViewById(R.id.buttonCancel)*/

        if (ActivityCompat.checkSelfPermission(this@MainActivity, Manifest.permission.READ_PHONE_STATE) != PERMISSION_GRANTED) {
            //toastLong("permission denied if block1")
            if (ActivityCompat.shouldShowRequestPermissionRationale(this@MainActivity, Manifest.permission.READ_PHONE_STATE )) {
                //toastLong("permission denied if block2")
            }else {
                ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_CALL_LOG), PERMISSION_REQUEST_CODE)
                //toastLong("permission else block1")
            }
        } else {
            //toastLong("permission else block2")
            Log.e("TAG", "onCreate: Permission Granted")
            /*ActivityCompat.requestPermissions(this@MainActivity,
                    arrayOf(Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_CALL_LOG),
                PERMISSION_GRANTED)*/
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.e("TAG", "onRequestPermissionsResult: $requestCode", )

        when(requestCode) {
            PERMISSION_REQUEST_CODE -> Log.e("TAG", "onRequestPermissionsResult: Granted" )
            else -> Log.e("TAG", "onRequestPermissionsResult: Denied" )
        }
    }

    /*val layoutParam = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                0,
                0,
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
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
        }*/
}