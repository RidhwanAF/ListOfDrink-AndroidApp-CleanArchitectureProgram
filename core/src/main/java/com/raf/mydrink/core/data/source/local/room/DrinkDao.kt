package com.raf.mydrink.core.data.source.local.room

import androidx.room.*
import com.raf.mydrink.core.data.source.local.entity.DrinkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DrinkDao {

    @Query("SELECT * FROM drink")
    fun getAllDrink(): Flow<List<DrinkEntity>>

    @Query("SELECT * FROM drink WHERE isFavorite = 1")
    fun getFavoriteDrink(): Flow<List<DrinkEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDrink(drink: List<DrinkEntity>)

    @Update
    fun updateFavoriteDrink(drink: DrinkEntity)
}