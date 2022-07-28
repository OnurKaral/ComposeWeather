package com.example.composeweather.features.common.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.composeweather.features.common.components.WeatherStateImage
import com.example.composeweather.model.WeatherMain
import com.example.composeweather.model.WeatherResponse
import com.example.composeweather.utils.formatDateTime

@Composable
fun WeatherRow(item: WeatherMain) {
    val imageURL = "https://openweathermap.org/img/wn/${item.weather.get(0).icon}.png"

    Surface(modifier = Modifier
        .padding(3.dp)
        .fillMaxWidth(),
        shape = CircleShape.copy(topEnd = CornerSize(3.dp)),
        color = Color.White) {
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = formatDateTime(item.dt).split(",")[0],
                modifier = Modifier.padding(4.dp))
            WeatherStateImage(imageURL = imageURL)
            Surface(modifier = Modifier.padding(1.dp),
                shape = CircleShape,
                color = Color(0xFFFFC400)) {
                Text(text = item.weather.get(0).main,
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onSecondary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 3.dp, bottom = 3.dp))
            }
            Text(text = "${item.main.temp}°C",
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(4.dp))
        }
    }
}

@Composable
fun SunSetSunRiseRow(data: WeatherResponse) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 10.dp, bottom = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween) {

        Row() {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Sunrise",
                modifier = Modifier.padding(4.dp))

            Text(text = formatDateTime(data.city.sunrise),
                style = MaterialTheme.typography.caption)
        }
        Row() {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Sunset",
                modifier = Modifier.padding(4.dp))


            Text(text = formatDateTime(data.city.sunrise),
                style = MaterialTheme.typography.caption)
        }
    }
}

@Composable
fun HumidityWindPressureRow(data: WeatherResponse) {

    Row(modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {

        Row(modifier = Modifier.padding(4.dp)) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Humidity",
                modifier = Modifier.size(20.dp))

            Text(text = "${data.list[0].main.humidity}%")
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Feels like",
                modifier = Modifier.size(20.dp))

            Text(text = "${data.list[0].main.feels_like}%")
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Pressure",
                modifier = Modifier.size(20.dp))

            Text(text = "${data.list[0].main.pressure}%")
        }

    }



}