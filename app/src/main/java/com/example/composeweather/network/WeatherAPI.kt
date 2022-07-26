package com.example.composeweather.network

import com.example.composeweather.model.WeatherResponse
import com.example.composeweather.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherAPI {
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("units") units: String = "metric",
        @Query("appid") appid: String = Constants.API_KEY): WeatherResponse
}