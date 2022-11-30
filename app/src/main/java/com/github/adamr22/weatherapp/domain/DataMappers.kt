package com.github.adamr22.weatherapp.domain

import com.github.adamr22.weatherapp.data.remote.Hourly
import com.github.adamr22.weatherapp.data.remote.NetworkModel
import com.github.adamr22.weatherapp.domain.models.WeatherInfo
import com.github.adamr22.weatherapp.domain.models.WeatherModel
import com.github.adamr22.weatherapp.domain.models.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class IndexedData(
    val index: Int,
    val data: WeatherModel
)

fun Hourly.toWeatherInfoMap(): Map<Int, List<WeatherModel>> {
    return time.mapIndexed { index, timeValue ->
        val time = LocalDateTime.parse(timeValue, DateTimeFormatter.ISO_DATE_TIME)
        val temperature = temperature_2m[index]
        val pressure = pressure_msl[index]
        val windSpeed = windspeed_10m[index]
        val humidity = relativehumidity_2m[index]
        val weatherType = WeatherType.fromWMO(weathercode[index])

        val day = time.dayOfWeek.name

        val data = WeatherModel(
            day,
            time,
            temperature,
            pressure,
            windSpeed,
            humidity,
            weatherType
        )

        IndexedData(index, data)

    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map { indexedData ->
            indexedData.data
        }
    }
}

fun NetworkModel.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = hourly.toWeatherInfoMap()
    val currentTime = LocalDateTime.now()

    val currentWeatherData = weatherDataMap[0]?.find {
        it.time.hour == currentTime.hour
    }

    return WeatherInfo(weatherDataMap, currentWeatherData)
}
