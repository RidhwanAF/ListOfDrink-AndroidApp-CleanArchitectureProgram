package com.raf.mydrink.core.domain.repository

import com.raf.mydrink.core.data.Resource
import com.raf.mydrink.core.domain.model.Drink
import kotlinx.coroutines.flow.Flow

interface IDrinkRepository {

    fun getAllDrink(): Flow<Resource<List<Drink>>>

    fun getFavoriteDrink(): Flow<List<Drink>>

    fun setFavoriteDrink(drink: Drink, state: Boolean)
}