package com.github.adamr22.weatherapp.data.remote

data class NetworkModel(
    val pressure_msl: List<Double>,
    val relativehumidity_2m: List<Int>,
    val temperature_2m: List<Double>,
    val time: List<String>,
    val weathercode: List<Int>,
    val windspeed_10m: List<Double>
)