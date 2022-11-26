package com.github.adamr22.weatherapp.domain.remote

import com.github.adamr22.weatherapp.data.remote.NetworkModel

interface NetworkRepository {
    suspend fun getWeatherData(lat: Long, lon: Long): NetworkModel
}