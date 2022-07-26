package com.example.composeweather.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composeweather.features.screens.MainScreen
import com.example.composeweather.features.screens.main.MainScreenViewModel
import com.example.composeweather.screens.WeatherSplashScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination =WeatherScreens.SplashScreen.name){
        composable(WeatherScreens.SplashScreen.name) {
            WeatherSplashScreen(navController = navController)
        }
        composable(WeatherScreens.MainScreen.name) {
            val mainScreenViewModel = hiltViewModel<MainScreenViewModel>()
            MainScreen(navController = navController,mainScreenViewModel)
        }
    }
}
