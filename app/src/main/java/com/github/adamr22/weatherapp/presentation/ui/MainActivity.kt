package com.github.adamr22.weatherapp.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.adamr22.weatherapp.presentation.theme.ThirtyPercentBlack
import com.github.adamr22.weatherapp.presentation.theme.UpdateCardColor
import com.github.adamr22.weatherapp.presentation.theme.WeatherAppTheme
import com.github.adamr22.weatherapp.presentation.theme.WeirdPurple

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CurrentMetrics()
                        TemperatureUpdateCard()
                    }
                }
            }
        }
    }
}

@Composable
fun CurrentMetrics(
    modifier: Modifier = Modifier
) {
    Spacer(modifier.height(30.dp))
    Column(
        modifier.fillMaxHeight(
            .5f
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Nairobi",
            fontSize = 34.sp,
            lineHeight = 41.sp,
            letterSpacing = .37.sp,
        )
        Text(
            text = "19\u2103",
            fontSize = 96.sp,
            lineHeight = 70.sp,
            letterSpacing = .37.sp,
        )
        Text(
            text = "Mostly Clear",
            fontSize = 20.sp,
            lineHeight = 24.sp,
            letterSpacing = .38.sp,
        )
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            Text(
                text = "H:24\u00B0",
                fontSize = 20.sp,
                lineHeight = 24.sp,
                letterSpacing = .38.sp,
            )
            Text(
                text = "L:18\u00B0",
                fontSize = 20.sp,
                lineHeight = 24.sp,
                letterSpacing = .38.sp,
            )
        }
    }
}

@Composable
fun TemperatureUpdateCard(modifier: Modifier = Modifier) {

    val alphabets = listOf("A", "B", "C", "D", "E", "F", "G", "H")

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
                        // TODO: Show hourly forecast
                    },
                    fontSize = 14.sp,
                )
                Text(
                    text = "Weekly Forecast",
                    modifier = modifier.clickable {
                        // TODO: Show weekly forecast
                    },
                    fontSize = 14.sp,
                )
            }
            Divider(
                color = ThirtyPercentBlack,
                thickness = 2.dp
            )
            Spacer(modifier = modifier.height(1.dp))
            LazyRow {
                itemsIndexed(alphabets) { _, alphabet ->
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
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = alphabet)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}