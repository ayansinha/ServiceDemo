package org.service.demo.view

import android.Manifest
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import org.service.demo.R
import org.service.demo.callrestrict.Constant
import org.service.demo.util.showSnackBar


class MainActivity : AppCompatActivity() {

    private val PERMISSION_REQUEST_CODE = 200
    private lateinit var rootView: ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rootView = findViewById(R.id.root)
        if (ActivityCompat.checkSelfPermission(this@MainActivity, Manifest.permission.READ_PHONE_STATE) != PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this@MainActivity, Manifest.permission.READ_PHONE_STATE )) {
                Log.d("TAG", "Permission Granted")
            }else {
                ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_CALL_LOG), PERMISSION_REQUEST_CODE)
            }
        } else {
            Log.d("TAG", "onCreate: Permission Granted")
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        Log.d("TAG", "onRequestPermissionsResult: $requestCode", )

        when(requestCode) {
            PERMISSION_REQUEST_CODE -> {
                Log.d("TAG", "onRequestPermissionsResult: Granted" )
                rootView.showSnackBar(Constant.PERMISSION_GRANTED)
            }
            else -> {
                Log.d("TAG", "onRequestPermissionsResult: Denied" )
                rootView.showSnackBar(Constant.PERMISSION_DENIED)
            }
        }
    }
}