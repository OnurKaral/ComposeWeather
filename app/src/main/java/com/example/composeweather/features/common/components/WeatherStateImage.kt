package com.example.composeweather.features.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun WeatherStateImage(imageURL: String) {
    Image(painter = rememberAsyncImagePainter(imageURL), contentDescription ="weather_image",
        modifier = Modifier.size(80.dp))

}