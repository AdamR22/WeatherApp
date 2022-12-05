package com.github.adamr22.weatherapp.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.adamr22.weatherapp.R
import com.github.adamr22.weatherapp.domain.models.WeatherInfo

@Composable
fun CurrentMetrics(
    modifier: Modifier = Modifier,
    data: WeatherInfo
) {
    Spacer(modifier.height(30.dp))

    Box(contentAlignment = Alignment.Center) {
        Column(
            modifier.fillMaxHeight(
                .5f
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${data.currentData?.temperatureCelsius}\u2103",
                fontSize = 50.sp,
            )
            data.currentData?.weatherType?.let {
                Text(
                    text = it.weatherDesc,
                    fontSize = 20.sp,
                    lineHeight = 24.sp,
                    letterSpacing = .38.sp,
                )
            }
            Spacer(modifier = modifier.height(20.dp))
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                data.currentData?.pressure?.let {
                    ExtraMetrics(
                        contentDescription = stringResource(id = R.string.atmospheric_pressure),
                        data = it,
                        icon = R.drawable.pressure,
                        unit = stringResource(id = R.string.atm_pressure_unit)
                    )
                }
                data.currentData?.humidity?.let {
                    ExtraMetrics(
                        contentDescription = stringResource(id = R.string.humidity),
                        data = it.toDouble(),
                        icon = R.drawable.drop,
                        unit = stringResource(id = R.string.percentage_sign)
                    )
                }
                data.currentData?.windSpeed?.let {
                    ExtraMetrics(
                        contentDescription = stringResource(id = R.string.wind_speed),
                        data = it,
                        icon = R.drawable.wind,
                        unit = stringResource(id = R.string.wind_speed_unit)
                    )
                }
            }
        }
    }
}