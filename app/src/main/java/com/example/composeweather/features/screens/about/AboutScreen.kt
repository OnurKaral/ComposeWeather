package com.example.composeweather.features.screens.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.composeweather.features.common.widgets.WeatherAppBar

@Composable
fun AboutScreen(navController: NavController) {
    Scaffold(topBar = {
        WeatherAppBar(
            title = "About",
            icon = Icons.Default.ArrowBack,
            isMainScreen = false,
            navController = navController){
                navController.popBackStack()
            }
    }) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "About",
                    style = MaterialTheme.typography.h6)

            }
        }
    }
}

