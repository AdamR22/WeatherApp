package com.github.adamr22.weatherapp.data.location

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import androidx.core.content.ContextCompat
import com.github.adamr22.weatherapp.common.Constants
import com.github.adamr22.weatherapp.domain.location.LocationRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject

@ExperimentalCoroutinesApi
class LocationRepositoryImpl @Inject constructor(
    private val locationProviderClient: FusedLocationProviderClient,
    private val application: Application
) : LocationRepository {

    private val locationManager =
        application.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    private val isGpsAvailable =
        locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )

    private fun checkFineLocationPermissionGranted() = ContextCompat.checkSelfPermission(
        application,
        Constants.fineLocationPermission
    ) == PackageManager.PERMISSION_GRANTED

    private fun checkCoarseLocationPermissionGranted() = ContextCompat.checkSelfPermission(
        application,
        Constants.coarseLocationPermission
    ) == PackageManager.PERMISSION_GRANTED

    @Suppress("DEPRECATION")
    private val locationRequest: LocationRequest = LocationRequest().apply {
        this.interval = 3600 * 1000
        this.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }


    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): Location? {

        if (!checkFineLocationPermissionGranted() || !checkCoarseLocationPermissionGranted() || !isGpsAvailable) {
            return null
        }

        return suspendCancellableCoroutine { coroutineWithValue ->
            locationProviderClient.requestLocationUpdates(
                locationRequest,
                object : LocationCallback() {
                    override fun onLocationResult(result: LocationResult) {
                        coroutineWithValue.resume(result.locations[0], null)
                    }
                },
                Looper.getMainLooper()
            )
            return@suspendCancellableCoroutine
        }
    }
}