package com.github.adamr22.weatherapp.data.remote

import com.github.adamr22.weatherapp.domain.Resource
import com.github.adamr22.weatherapp.domain.models.WeatherInfo
import com.github.adamr22.weatherapp.domain.remote.NetworkRepository
import com.github.adamr22.weatherapp.domain.toWeatherInfo
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val api: NetworkService
) : NetworkRepository {
    override suspend fun getWeatherData(lat: Long, lon: Long): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherInfo(lat, lon).toWeatherInfo()
            )
        } catch (e: Exception) {
            Resource.Error(
                e.message
                    ?: "An unknown error occurred. If first time using application, ensure you are connected to the internet"
            )
        }
    }
}