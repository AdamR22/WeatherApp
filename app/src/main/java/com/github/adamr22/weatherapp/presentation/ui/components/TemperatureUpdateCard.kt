package com.github.adamr22.weatherapp.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.adamr22.weatherapp.R
import com.github.adamr22.weatherapp.domain.models.WeatherInfo
import com.github.adamr22.weatherapp.domain.models.WeatherModel
import com.github.adamr22.weatherapp.presentation.theme.ThirtyPercentBlack
import com.github.adamr22.weatherapp.presentation.theme.UpdateCardColor
import java.time.LocalDateTime

@Composable
fun TemperatureUpdateCard(
    modifier: Modifier = Modifier,
    data: WeatherInfo
) {

    val hourlyData: List<WeatherModel> = remember { data.weatherData[0]!! }
    val allData = remember { data.weatherData }

    val hourlyUpdates = remember { mutableStateOf(true) }

    Card(
        modifier = modifier.fillMaxHeight(),
        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        backgroundColor = UpdateCardColor,
    ) {
        Column(
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(4.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.hourly_forecast),
                    modifier = modifier.clickable {
                        hourlyUpdates.value = true
                    },
                    fontSize = 14.sp,
                )
                Text(
                    text = stringResource(id = R.string.weekly_forecast),
                    modifier = modifier.clickable {
                        hourlyUpdates.value = false
                    },
                    fontSize = 14.sp,
                )
            }
            Divider(
                color = ThirtyPercentBlack,
                thickness = 2.dp
            )
            Spacer(modifier = modifier.height(1.dp))
            Column(
                modifier = modifier
                    .verticalScroll(rememberScrollState())
                    .padding(8.dp)
            ) {
                if (hourlyUpdates.value) {
                    LazyRow {
                        items(hourlyData) { data ->
                            HourlyWeatherCard(data = data)
                        }
                    }
                } else {
                    allData.forEach { (index, list) ->
                        Column {
                            Text(
                                text = if (list[index].time.dayOfWeek.name == LocalDateTime.now().dayOfWeek.name
                                ) stringResource(id = R.string.today) else list[index].time.dayOfWeek.name
                            )
                            LazyRow {
                                items(list) { data ->
                                    HourlyWeatherCard(data = data)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}