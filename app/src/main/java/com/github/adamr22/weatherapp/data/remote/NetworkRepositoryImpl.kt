package com.github.adamr22.weatherapp.data.remote

import com.github.adamr22.weatherapp.domain.remote.NetworkRepository
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val api: NetworkService
) : NetworkRepository{
    override suspend fun getWeatherData(lat: Long, lon: Long): NetworkModel {
        return api.getWeatherInfo(lat, lon)
    }
}