package com.example.composeweather.di

import android.content.Context
import androidx.room.Room
import com.example.composeweather.data.WeatherAppDao
import com.example.composeweather.data.WeatherAppDatabase
import com.example.composeweather.network.WeatherAPI
import com.example.composeweather.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideDao(weatherAppDatabase: WeatherAppDatabase): WeatherAppDao = weatherAppDatabase.weatherDao()

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): WeatherAppDatabase =
        Room.databaseBuilder(context, WeatherAppDatabase::class.java, Constants.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun WeatherApi(): WeatherAPI {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherAPI::class.java)
    }
}