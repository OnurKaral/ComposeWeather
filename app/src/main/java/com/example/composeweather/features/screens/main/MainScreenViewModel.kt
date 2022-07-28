package com.example.composeweather.features.screens.main

import androidx.lifecycle.ViewModel
import com.example.composeweather.data.DataOrException
import com.example.composeweather.model.WeatherResponse
import com.example.composeweather.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val repository: WeatherRepository):ViewModel() {
    suspend fun getWeatherData(city: String)
            : DataOrException<WeatherResponse, Boolean, Exception> {
        return repository.getWeather(city = city)

    }

}
