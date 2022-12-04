package com.github.adamr22.weatherapp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.adamr22.weatherapp.domain.Resource
import com.github.adamr22.weatherapp.domain.location.LocationRepository
import com.github.adamr22.weatherapp.domain.remote.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherAppViewModel @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val locationRepository: LocationRepository
) : ViewModel() {

    var state by mutableStateOf(WeatherState())
        private set

    fun loadData() = viewModelScope.launch {

        state = state.copy(
            isLoading = true
        )

        locationRepository.getCurrentLocation()?.let {

            state = when (val result =
                networkRepository.getWeatherData(it.latitude, it.longitude)) {

                is Resource.Success -> state.copy(
                    weatherInfo = result.data,
                    isLoading = false
                )
                is Resource.Error -> state.copy(
                    isLoading = false,
                    error = result.errorMessage
                )
            }

        } ?: kotlin.run {
            state = state.copy(
                isLoading = false,
                error = "Ensure location permission is enabled"
            )

        }
    }
}