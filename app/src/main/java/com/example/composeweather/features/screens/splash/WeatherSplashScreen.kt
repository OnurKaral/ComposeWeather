package com.example.composeweather.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composeweather.R
import com.example.composeweather.navigation.WeatherScreens
import kotlinx.coroutines.delay


@Composable
fun WeatherSplashScreen(navController: NavController) {
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }
    LaunchedEffect(key1 = true, block = {
        scale.animateTo(0.8f, animationSpec = tween(durationMillis = 800,
        easing = {
            OvershootInterpolator(8f).getInterpolation(it)
        }))
        delay(2000L)
        navController.navigate(WeatherScreens.MainScreen.name)
    })

    Surface(modifier = Modifier
        .padding(15.dp)
        .size(330.dp)
        .scale(scale.value), shape = CircleShape, color = Color.White) {
    
        Column(modifier = Modifier.padding(1.dp), horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
            Image(painter = painterResource(id = R.drawable.sun), contentDescription ="splash_image",
            contentScale = ContentScale.Fit, modifier = Modifier.size(100.dp))
            Text(text = "Weather Compose App", style = MaterialTheme.typography.h5, modifier = Modifier.padding(10.dp))
        }

    }
}
