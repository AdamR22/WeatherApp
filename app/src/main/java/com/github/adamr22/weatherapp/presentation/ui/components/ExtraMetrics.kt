package com.github.adamr22.weatherapp.presentation.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ExtraMetrics(
    modifier: Modifier = Modifier,
    contentDescription: String,
    data: Double,
    @DrawableRes icon: Int,
    unit: String,
) {
    Row {
        Icon(
            modifier = modifier.size(24.dp),
            painter = painterResource(id = icon),
            contentDescription = contentDescription
        )
        Spacer(modifier.width(5.dp))
        Text(text = data.toString())
        Spacer(modifier.width(5.dp))
        Text(text = unit)
        Spacer(modifier.width(10.dp))
    }
}