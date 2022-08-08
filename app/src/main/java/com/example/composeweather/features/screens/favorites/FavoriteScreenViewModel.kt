package com.example.composeweather.features.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeweather.model.Favorite
import com.example.composeweather.repository.WeatherDBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteScreenViewModel @Inject constructor(private val repository: WeatherDBRepository): ViewModel() {

    private val favorites = MutableStateFlow<List<Favorite>>(emptyList())
    val fav_list = favorites.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getFavorites().distinctUntilChanged().collect {listOfFavs ->
                if(listOfFavs.isNotEmpty()) {
                    favorites.value = listOfFavs
                }

            }
        }
    }
    fun insertFavorite(favorite: Favorite) {
        viewModelScope.launch {
            repository.insertFavorite(favorite)
        }
    }
    fun updateFavorite(favorite: Favorite) {
        viewModelScope.launch {
            repository.updateFavorite(favorite)
        }
    }
    fun deleteFavorite(favorite: Favorite) {
        viewModelScope.launch {
            repository.deleteFavorite(favorite)
        }
    }
}
