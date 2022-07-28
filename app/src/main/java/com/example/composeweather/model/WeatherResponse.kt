package com.example.composeweather.model

data class WeatherResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherMain>,
    val message: Int
)