package com.github.adamr22.weatherapp.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.github.adamr22.weatherapp.domain.models.WeatherModel
import com.github.adamr22.weatherapp.presentation.theme.WeirdPurple
import java.time.format.DateTimeFormatter

@Composable
fun HourlyWeatherCard(
    modifier: Modifier = Modifier,
    data: WeatherModel
) {
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