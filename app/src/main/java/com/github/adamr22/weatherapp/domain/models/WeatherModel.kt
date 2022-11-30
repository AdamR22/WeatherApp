package com.github.adamr22.weatherapp.domain.models

import java.time.LocalDateTime

data class WeatherModel(
    val day: String,
    val time: LocalDateTime,
    val temperatureCelsius: Double,
    val pressure: Double,
    val windSpeed: Double,
    val humidity: Int,
    val weatherType: WeatherType
)
