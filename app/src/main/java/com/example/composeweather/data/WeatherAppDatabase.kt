package com.example.composeweather.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composeweather.model.Favorite

@Database(entities = [Favorite::class], version = 1, exportSchema = false)
abstract class WeatherAppDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherAppDao

}