package org.service.demo.location

import android.annotation.SuppressLint
import android.app.Service
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.IBinder
import android.os.Looper
import android.util.Log
import com.google.android.gms.location.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.service.demo.util.LocationPrefs
import org.service.demo.view.MainActivity
import java.io.IOException
import java.util.*

class FusedLocationService: Service() {
    private var job: Job? = null
    private val scope = CoroutineScope(IO + SupervisorJob())
    private var fusedLocationProviderClient: FusedLocationProviderClient? = null
    private var locationRequest: LocationRequest? = null
    private var locationCallback: LocationCallback? = null
    private var currentLocation: Location? = null
    private val geocoder = Geocoder(this, Locale.getDefault())
    private var PRIVATE_MODE = 0

    var addresses: List<Address> = emptyList()

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
    }

    @SuppressLint("MissingPermission")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("TAG", "onStartCommand: ")
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        job = scope.launch(IO) {
            launch {
                locationRequest = LocationRequest().apply {
                    interval = 1000
                    smallestDisplacement = 100f // in meters, here its 1000mts
                    priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                }
            }
            launch {
                locationCallback = object : LocationCallback() {
                    override fun onLocationResult(locationResult: LocationResult?) {
                        super.onLocationResult(locationResult)
                        if (locationResult?.lastLocation != null) {
                            currentLocation = locationResult.lastLocation
                            locationResult.lastLocation.also { //result ->
                                try {
                                    addresses = geocoder.getFromLocation(
                                        51.9179328, 0.9154783, 1
                                        /*result.latitude,
                                        result.longitude,
                                        1*/
                                    )
                                    Log.d("TAG", "onLocationResult COUNTRY_CODE: ${addresses[0].countryCode}") // GB
                                    Log.d("TAG", "onLocationResult ADMIN_AREA: ${addresses[0].adminArea}") // England
                                    Log.d("TAG", "onLocationResult COUNTRY_NAME: ${addresses[0].countryName}") // United Kingdom
                                    Log.d("TAG", "onLocationResult POSTAL_CODE: ${addresses[0].postalCode}") // original value -> CO4 9WJ. chris value -> CO4 9EY

                                    val postalCode = addresses[0].postalCode.take(3)
                                    val desc = "Tier Two in (High Alert)"
                                    val locationPrefs = LocationPrefs(this@FusedLocationService)
                                    locationPrefs.apply {
                                        savePostalCode(postalCode)
                                        saveDescription(desc)
                                    }

                                }catch (io: IOException) {
                                    Log.d("TAG", "onLocationResult: EXCEPTION")
                                }
                            }

                        }
                    }
                }
                fusedLocationProviderClient.apply {
                    this?.requestLocationUpdates(
                        locationRequest,
                        locationCallback,
                        Looper.getMainLooper()
                    )
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d("TAG", "onDestroy: ")
        job?.cancel()
        super.onDestroy()
    }
}