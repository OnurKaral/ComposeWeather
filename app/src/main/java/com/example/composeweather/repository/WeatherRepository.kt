package com.example.composeweather.repository

import android.util.Log
import com.example.composeweather.data.DataOrException
import com.example.composeweather.model.WeatherResponse
import com.example.composeweather.network.WeatherAPI
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherApi: WeatherAPI) {
    suspend fun getWeather(city: String): DataOrException<WeatherResponse, Boolean, Exception> {
            val response = try {
            weatherApi.getWeather(city = city)
        } catch (e: Exception) {
            Log.d("WeatherRepository", "Error: ${e.message}")
            return DataOrException(error = e)
        }
        return DataOrException(data = response)
    }
}