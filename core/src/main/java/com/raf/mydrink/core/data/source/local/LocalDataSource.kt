package com.raf.mydrink.core.data.source.local

import com.raf.mydrink.core.data.source.local.entity.DrinkEntity
import com.raf.mydrink.core.data.source.local.room.DrinkDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val drinkDao: DrinkDao){

    fun getAllDrink(): Flow<List<DrinkEntity>> = drinkDao.getAllDrink()

    fun getFavoriteDrink(): Flow<List<DrinkEntity>> = drinkDao.getFavoriteDrink()

    suspend fun insertDrink(drinkList: List<DrinkEntity>) = drinkDao.insertDrink(drinkList)

    fun setFavoriteDrink(drink: DrinkEntity, newState: Boolean) {
        drink.isFavorite = newState
        drinkDao.updateFavoriteDrink(drink)
    }
}