package com.github.adamr22.weatherapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("v1/forecast?hourly=temperature_2m,relativehumidity_2m,weathercode,pressure_msl,windspeed_10m")
    suspend fun getWeatherInfo(
        @Query("latitude") lat: Long,
        @Query("longitude") lon: Long
    ): NetworkModel

}