package com.example.composeweather.features.common.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun WeatherAppBar(
    title: String = "Title",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    navController: NavController,
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit= {}) {
        
    TopAppBar(title = {
                      Text(text = title)
    }, actions = {
                 if(isMainScreen){
                     IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Search,
                            contentDescription = "Search")
                     }
                     IconButton(onClick = { /*TODO*/ }) {
                         Icon(imageVector = Icons.Rounded.MoreVert,
                             contentDescription = "More")
                     }
                 }else Box{}
    }, navigationIcon = {
                        if(icon != null) {
                            Icon(imageVector = icon,
                                tint = MaterialTheme.colors.onSecondary,
                                modifier = Modifier.clickable {
                                    onButtonClicked.invoke()
                                }.padding(horizontal = 5.dp),
                                contentDescription = "Navigation")

                        }
    }, backgroundColor = Color.Transparent,
    elevation = elevation)
}







