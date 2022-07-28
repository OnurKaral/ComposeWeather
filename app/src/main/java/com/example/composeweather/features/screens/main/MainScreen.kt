package com.example.composeweather.features.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.composeweather.data.DataOrException
import com.example.composeweather.features.common.components.WeatherStateImage
import com.example.composeweather.features.common.widgets.WeatherAppBar
import com.example.composeweather.model.WeatherMain
import com.example.composeweather.model.WeatherResponse
import com.example.composeweather.utils.formatDate
import com.example.composeweather.utils.formatDateTime


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
    val imageURL = "https://openweathermap.org/img/wn/${data.list.get(0).weather[0].icon}.png"
       Column(modifier = Modifier
           .padding(4.dp)
           .fillMaxWidth(),
       verticalArrangement = Arrangement.Center,
       horizontalAlignment = Alignment.CenterHorizontally) {

               Text(text = formatDate(data.list[0].dt),
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

                                 Text(text = "Weather: ${data.list[0].weather[0].description}",
                                        style = MaterialTheme.typography.caption,
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(4.dp))
                           Text(text = "${data.list[0].main.temp}°C",
                                        style = MaterialTheme.typography.caption,
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(4.dp))



                       }

               }
           HumidityWindPressureRow(data = data)
           Divider()
           SunSetSunRiseRow(data = data)
           Text(text = "This Week",
               style = MaterialTheme.typography.subtitle2,
               fontWeight = FontWeight.Bold)

           Surface(modifier = Modifier
               .fillMaxWidth()
               .fillMaxHeight(),
                   shape = RoundedCornerShape(size = 15.dp),
                   color = Color(0xFFFAFAFA)) {
               LazyColumn(modifier = Modifier.padding(5.dp),
                   contentPadding = PaddingValues(1.dp)) {

                    items(items = data.list) { item: WeatherMain ->
                        WeatherRow(item = item)
                    }
                   }
               
               

           }
       }
}

@Composable
fun WeatherRow(item: WeatherMain) {
    val imageURL = "https://openweathermap.org/img/wn/${item.weather.get(0).icon}.png"
    
    Surface(modifier = Modifier
        .padding(3.dp)
        .fillMaxWidth(),
    shape = CircleShape.copy(topEnd = CornerSize(3.dp)),
    color = Color.White) {
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = formatDateTime(item.dt).split(",")[0],
                modifier = Modifier.padding(4.dp))
            WeatherStateImage(imageURL = imageURL)
            Surface(modifier = Modifier.padding(1.dp),
            shape = CircleShape,
            color = Color(0xFFFFC400)) {
                Text(text = "${item.weather.get(0).main}",
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onSecondary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 3.dp, bottom = 3.dp))
            }
            Text(text = "${item.main.temp}°C",
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(4.dp))
        }
    }
}



@Composable
fun SunSetSunRiseRow(data: WeatherResponse) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 10.dp, bottom = 5.dp),
    horizontalArrangement = Arrangement.SpaceBetween) {

        Row() {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Sunrise",
                    modifier = Modifier.padding(4.dp))

            Text(text = formatDateTime(data.city.sunrise),
                style = MaterialTheme.typography.caption)
        }
        Row() {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Sunset",
                    modifier = Modifier.padding(4.dp))


            Text(text = formatDateTime(data.city.sunrise),
            style = MaterialTheme.typography.caption)
        }
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

            Text(text = "${data.list[0].main.humidity}%")
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Feels like",
                modifier = Modifier.size(20.dp))

            Text(text = "${data.list[0].main.feels_like}%")
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Pressure",
                modifier = Modifier.size(20.dp))

            Text(text = "${data.list[0].main.pressure}%")
        }

    }



}


