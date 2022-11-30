package com.github.adamr22.weatherapp.domain.models

data class WeatherInfo(
    val weatherData: Map<Int, List<WeatherModel>>,
    val currentData: WeatherModel?
)
