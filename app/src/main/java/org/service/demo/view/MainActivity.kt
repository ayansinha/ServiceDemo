package org.service.demo.view

import android.Manifest
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.service.demo.NHSLocationWidget
import org.service.demo.R
import org.service.demo.callrestrict.Constant
import org.service.demo.location.FusedLocationService
import org.service.demo.util.showSnackBar


class MainActivity : AppCompatActivity() {

    private var PERMISSION_REQUEST_CODE = 200
    private lateinit var rootView: ConstraintLayout

    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val man = AppWidgetManager.getInstance(this@MainActivity)
        val ids = man.getAppWidgetIds(ComponentName(this@MainActivity, NHSLocationWidget::class.java))
        val updateIntent = Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE)
        updateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
        sendBroadcast(updateIntent)
        rootView = findViewById(R.id.root)

        if (ActivityCompat.checkSelfPermission(this@MainActivity, Manifest.permission.READ_PHONE_STATE) != PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this@MainActivity, Manifest.permission.READ_PHONE_STATE )) {
                Log.d("TAG", "Permission Granted")
            }else {
                ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_CALL_LOG , Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_REQUEST_CODE)
            }
        } else {
            Log.d("TAG", "onCreate: Permission Granted")
        }
        /*if ((ActivityCompat.checkSelfPermission(this@MainActivity, Manifest.permission.READ_PHONE_STATE) +
            ActivityCompat.checkSelfPermission(this@MainActivity, Manifest.permission.READ_CALL_LOG) +
            ActivityCompat.checkSelfPermission(this@MainActivity, Manifest.permission.ACCESS_FINE_LOCATION)) != PERMISSION_GRANTED) {

            if ((ActivityCompat.shouldShowRequestPermissionRationale(this@MainActivity, Manifest.permission.READ_PHONE_STATE))
                        || (ActivityCompat.shouldShowRequestPermissionRationale(this@MainActivity, Manifest.permission.READ_CALL_LOG))
                        || (ActivityCompat.shouldShowRequestPermissionRationale(this@MainActivity, Manifest.permission.ACCESS_FINE_LOCATION))) {
                            Log.d("TAG", "Permission Granted")
                ActivityCompat.requestPermissions(this@MainActivity,
                    arrayOf(
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.READ_CALL_LOG,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ),
                    PERMISSION_REQUEST_CODE
                )

            }
        } else {
            Log.d("TAG", "onCreate: Permission Granted")
        }*/
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        Log.d("TAG", "onRequestPermissionsResult: $requestCode")

        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                Log.d("TAG", "onRequestPermissionsResult: Granted")
                startService(Intent(this@MainActivity, FusedLocationService::class.java))
                rootView.showSnackBar(Constant.PERMISSION_GRANTED)
            }
            else -> {
                Log.d("TAG", "onRequestPermissionsResult: Denied")
                rootView.showSnackBar(Constant.PERMISSION_DENIED)
            }
        }
    }

    /*override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("REQUEST_CODE" , PERMISSION_REQUEST_CODE)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        PERMISSION_REQUEST_CODE = savedInstanceState.getInt("REQUEST_CODE")
    }*/

    override fun onResume() {
        super.onResume()
        if (PERMISSION_REQUEST_CODE == 200) {
            Log.d("TAG", "onResume: $PERMISSION_REQUEST_CODE")
            rootView.showSnackBar(Constant.PERMISSION_GRANTED)
            startService(Intent(this@MainActivity, FusedLocationService::class.java))
        }

        Log.d("TAG", "onResume: ")
    }
}