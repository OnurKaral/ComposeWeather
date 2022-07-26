package com.example.composeweather.features.screens

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.composeweather.data.DataOrException
import com.example.composeweather.features.screens.main.MainScreenViewModel
import com.example.composeweather.model.WeatherResponse


@Composable
fun MainScreen(
        navController: NavHostController,
        mainScreenViewModel: MainScreenViewModel = hiltViewModel()) {
       ShowData(mainScreenViewModel)
}

@Composable
fun ShowData(mainScreenViewModel: MainScreenViewModel){
        val weatherData =produceState<DataOrException<WeatherResponse,Boolean,Exception>>(
                initialValue =DataOrException(isLoading = true)){
                value = mainScreenViewModel.getWeatherData("London")
        }.value

        if(weatherData.isLoading == true){
                CircularProgressIndicator()
        }else if(weatherData.data != null){
                Text(text = weatherData.data.weather.get(0).description)
        }


}