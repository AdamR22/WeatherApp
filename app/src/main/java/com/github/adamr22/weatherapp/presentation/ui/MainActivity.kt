package com.github.adamr22.weatherapp.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.github.adamr22.weatherapp.common.Constants.coarseLocationPermission
import com.github.adamr22.weatherapp.common.Constants.fineLocationPermission
import com.github.adamr22.weatherapp.presentation.WeatherAppViewModel
import com.github.adamr22.weatherapp.presentation.theme.WeatherAppTheme
import com.github.adamr22.weatherapp.presentation.ui.components.CurrentMetrics
import com.github.adamr22.weatherapp.presentation.ui.components.TemperatureUpdateCard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<WeatherAppViewModel>()

    private val permissionHandler =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            viewModel.loadData()
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        permissionHandler.launch(
            arrayOf(
                fineLocationPermission,
                coarseLocationPermission
            )
        )

        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    if (viewModel.state.error != null) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = viewModel.state.error!!,
                                fontSize = 14.sp,
                            )
                        }
                    }

                    if (viewModel.state.isLoading) {
                        Box(contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }

                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        viewModel.state.weatherInfo?.let {
                            CurrentMetrics(data = it)
                            TemperatureUpdateCard(data = it)
                        } ?: Text(
                            text = viewModel.state.error!!,
                            fontSize = 14.sp,
                        )
                    }
                }
            }
        }
    }
}