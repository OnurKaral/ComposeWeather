package com.example.composeweather.features.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import coil.compose.rememberImagePainter
import com.example.composeweather.data.DataOrException
import com.example.composeweather.features.common.widgets.WeatherAppBar
import com.example.composeweather.model.WeatherResponse
import com.example.composeweather.utils.formatDate


@Composable
fun MainScreen(
        navController: NavHostController,
        mainScreenViewModel: MainScreenViewModel = hiltViewModel()) {
        val weatherData =produceState<DataOrException<WeatherResponse,Boolean,Exception>>(
                initialValue =DataOrException(isLoading = true)){
                value = mainScreenViewModel.getWeatherData("istanbul")
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
    val imageURL = "https://openweathermap.org/img/wn/${data.weather[0].icon}.png"
       Column(modifier = Modifier
           .padding(4.dp)
           .fillMaxWidth(),
       verticalArrangement = Arrangement.Center,
       horizontalAlignment = Alignment.CenterHorizontally) {

               Text(text = formatDate(data.dt),
               style = MaterialTheme.typography.caption,
               color = MaterialTheme.colors.onSecondary,
               fontWeight = FontWeight.Bold,
               modifier = Modifier.padding(4.dp))
               
               
               Surface(modifier = Modifier
                   .padding(4.dp)
                   .size(200.dp),
                       shape = CircleShape,
               color = Color(0xFFFFC400)) {

                       Column(
                               verticalArrangement = Arrangement.Center,
                               horizontalAlignment = Alignment.CenterHorizontally) {

                           WeatherStateImage(imageURL = imageURL)

                                 Text(text = "Weather: ${data.weather[0].main}",
                                        style = MaterialTheme.typography.caption,
                                        color = MaterialTheme.colors.onSecondary,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(4.dp))
                           Text(text = "${data.main.temp}°C",
                                        style = MaterialTheme.typography.caption,
                                        color = MaterialTheme.colors.onSecondary,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(4.dp))



                       }

               }
           HumidityWindPressureRow(data = data)
           Divider()
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

            Text(text = "${data.main.humidity}%")
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Feels like",
                modifier = Modifier.size(20.dp))

            Text(text = "${data.main.feels_like}%")
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Pressure",
                modifier = Modifier.size(20.dp))

            Text(text = "${data.main.pressure}%")
        }

    }



}

@Composable
fun WeatherStateImage(imageURL: String) {
    Image(painter = rememberImagePainter(imageURL), contentDescription ="weather_image",
            modifier = Modifier.size(80.dp))

}
