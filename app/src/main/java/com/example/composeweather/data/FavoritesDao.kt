package com.example.composeweather.data

import androidx.room.*
import com.example.composeweather.model.City
import com.example.composeweather.model.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {
    @Query("SELECT * FROM favorite_tbl")
    fun getFavorites(): Flow<List<Favorite>>

    @Query("SELECT * FROM favorite_tbl where city = :city")
    suspend fun getFavorite(city: String): Favorite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavorite(favorite: Favorite)

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)

    @Query("DELETE FROM favorite_tbl")
    suspend fun deleteAllFavorite()
}