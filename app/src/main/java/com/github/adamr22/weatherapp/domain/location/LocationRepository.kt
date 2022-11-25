package com.github.adamr22.weatherapp.domain.location

import android.location.Location

interface LocationRepository {
    suspend fun getCurrentLocation(): Location?
}