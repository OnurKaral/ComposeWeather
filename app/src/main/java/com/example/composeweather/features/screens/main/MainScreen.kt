package com.example.composeweather.features.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.composeweather.features.screens.main.MainScreenViewModel


@Composable
fun MainScreen(
        navController: NavHostController,
        mainScreenViewModel: MainScreenViewModel = hiltViewModel()) {
        Text(text = "Main Screen")
}
