package com.example.composeweather.features.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.composeweather.data.DataOrException
import com.example.composeweather.features.common.widgets.WeatherAppBar
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
       Column(modifier = Modifier
               .padding(4.dp)
               .fillMaxWidth(),
       verticalArrangement = Arrangement.Center,
       horizontalAlignment = Alignment.CenterHorizontally) {

               Text(text = "Temperature: ${data.main.temp}",
               style = MaterialTheme.typography.caption,
               color = MaterialTheme.colors.onSecondary,
               fontWeight = FontWeight.Bold,
               modifier = Modifier.padding(4.dp))
               
               
               Surface(modifier = Modifier.padding(4.dp).size(200.dp),
                       shape = CircleShape,
               color = Color.Green) {

                       Column(
                               verticalArrangement = Arrangement.Center,
                               horizontalAlignment = Alignment.CenterHorizontally) {
                                 Text(text = "Humidity: ${data.main.humidity}",
                                        style = MaterialTheme.typography.caption,
                                        color = MaterialTheme.colors.onSecondary,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(4.dp))

                       }
               }
       }
}
