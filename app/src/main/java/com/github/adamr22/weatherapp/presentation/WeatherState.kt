package com.github.adamr22.weatherapp.presentation

import com.github.adamr22.weatherapp.domain.models.WeatherInfo

data class WeatherState (
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)