package com.github.adamr22.weatherapp.data.location

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.github.adamr22.weatherapp.common.Constants
import com.github.adamr22.weatherapp.domain.location.LocationRepository
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val locationProviderClient: FusedLocationProviderClient,
    private val context: Context
) : LocationRepository {

    private val locationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    private val isGpsAvailable =
        locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )

    private fun checkFineLocationPermissionGranted() = ContextCompat.checkSelfPermission(
        context,
        Constants.fineLocationPermission
    ) == PackageManager.PERMISSION_GRANTED

    private fun checkCoarseLocationPermissionGranted() = ContextCompat.checkSelfPermission(
        context,
        Constants.coarseLocationPermission
    ) == PackageManager.PERMISSION_GRANTED


    @SuppressLint("MissingPermission")
    @ExperimentalCoroutinesApi
    override suspend fun getCurrentLocation(): Location? {

        if (!checkFineLocationPermissionGranted() || !checkCoarseLocationPermissionGranted() || !isGpsAvailable) {
            return null
        }

        return suspendCancellableCoroutine { coroutineWithValue ->
            locationProviderClient.lastLocation.apply {
                if (isComplete) {
                    if (isSuccessful) {
                        coroutineWithValue.resume(result, null)
                    } else {
                        coroutineWithValue.resume(null, null)
                    }

                    return@suspendCancellableCoroutine
                }
                addOnCanceledListener {
                    coroutineWithValue.cancel()
                }
                addOnSuccessListener {
                    coroutineWithValue.resume(it, null)
                }
                addOnFailureListener {
                    coroutineWithValue.resume(null, null)
                }
            }
        }
    }
}