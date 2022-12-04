package com.github.adamr22.weatherapp.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.adamr22.weatherapp.domain.models.WeatherInfo
import com.github.adamr22.weatherapp.domain.models.WeatherModel
import com.github.adamr22.weatherapp.presentation.theme.ThirtyPercentBlack
import com.github.adamr22.weatherapp.presentation.theme.UpdateCardColor
import com.github.adamr22.weatherapp.presentation.theme.WeirdPurple
import java.time.format.DateTimeFormatter

@Composable
fun TemperatureUpdateCard(
    modifier: Modifier = Modifier,
    data: WeatherInfo
) {

    val hourlyData: List<WeatherModel> = data.weatherData[0]!!
    val allData = data.weatherData

    var hourlyUpdates = true

    Card(
        modifier = modifier.fillMaxHeight(),
        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        backgroundColor = UpdateCardColor,
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(4.dp),
            ) {
                Text(
                    text = "Hourly Forecast",
                    modifier = modifier.clickable {
                        hourlyUpdates = true
                    },
                    fontSize = 14.sp,
                )
                Text(
                    text = "Weekly Forecast",
                    modifier = modifier.clickable {
                        hourlyUpdates = false
                    },
                    fontSize = 14.sp,
                )
            }
            Divider(
                color = ThirtyPercentBlack,
                thickness = 2.dp
            )
            Spacer(modifier = modifier.height(1.dp))
            if (hourlyUpdates) {
                LazyRow {
                    items(hourlyData) { data ->
                        Card(
                            modifier = modifier
                                .padding(5.dp)
                                .width(60.dp)
                                .height(146.dp),
                            shape = RoundedCornerShape(30.dp),
                            backgroundColor = WeirdPurple
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Text(text = data.time.format(DateTimeFormatter.ofPattern("HH:mm")))
                                Image(
                                    modifier = modifier.size(24.dp),
                                    painter = painterResource(id = data.weatherType.iconRes),
                                    contentDescription = data.weatherType.weatherDesc
                                )
                                Text(text = "${data.temperatureCelsius}\u2103")
                            }
                        }
                    }
                }
            } else {
                Box(contentAlignment = Alignment.Center) {
                    Text(text = "Soon")
                }
            }
        }
    }
}