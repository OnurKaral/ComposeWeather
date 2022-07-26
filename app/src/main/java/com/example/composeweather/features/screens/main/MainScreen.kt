package com.example.composeweather.features.screens

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.composeweather.data.DataOrException
import com.example.composeweather.features.common.widgets.WeatherAppBar
import com.example.composeweather.features.screens.main.MainScreenViewModel
import com.example.composeweather.model.WeatherResponse


@Composable
fun MainScreen(
        navController: NavHostController,
        mainScreenViewModel: MainScreenViewModel = hiltViewModel()) {
        val weatherData =produceState<DataOrException<WeatherResponse,Boolean,Exception>>(
                initialValue =DataOrException(isLoading = true)){
                value = mainScreenViewModel.getWeatherData("London")
        }.value

        if(weatherData.isLoading == true){
                CircularProgressIndicator()
        }else if(weatherData.data != null){
                MainScaffold(weatherResponse = weatherData.data,navController)

        }
}

@Composable
fun MainScaffold(weatherResponse: WeatherResponse,
                 navController: NavHostController){
        Scaffold(topBar = {
                WeatherAppBar("London",navController = navController,
                        icon = Icons.Default.ArrowBack,
                elevation = 3.dp)
        }) {
                MainContent(data = weatherResponse)
        }
}

@Composable
fun MainContent(data: WeatherResponse) {
        Text(text = data.toString())
}
