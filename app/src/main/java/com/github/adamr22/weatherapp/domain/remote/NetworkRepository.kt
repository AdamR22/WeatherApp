package com.github.adamr22.weatherapp.domain.remote

import com.github.adamr22.weatherapp.domain.Resource
import com.github.adamr22.weatherapp.domain.models.WeatherInfo

interface NetworkRepository {
    suspend fun getWeatherData(lat: Long, lon: Long): Resource<WeatherInfo>
}