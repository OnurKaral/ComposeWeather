package com.example.composeweather.features.screens.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeweather.data.DataOrException
import com.example.composeweather.model.WeatherResponse
import com.example.composeweather.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val repository: WeatherRepository):ViewModel() {
    val data: MutableState<DataOrException<WeatherResponse,Boolean,Exception>> = mutableStateOf(DataOrException(null,true,Exception("")))

    init {
        getdata()
    }

    private fun getdata() {
        getWeather(city = "London")
    }

    private fun getWeather(city: String) {
        viewModelScope.launch {
            if(city.isEmpty()) return@launch
            data.value.isLoading = true
            data.value = repository.getWeather(city)
            if (data.value.data.toString().isNotEmpty()) data.value.isLoading = false
        }
        Log.d("MainScreenViewModel", "getWeather: ${data.value.data.toString()}")
    }
}
