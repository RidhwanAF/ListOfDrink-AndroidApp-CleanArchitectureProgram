package com.raf.mydrink.detail

import androidx.lifecycle.ViewModel
import com.raf.mydrink.core.domain.model.Drink
import com.raf.mydrink.core.domain.usecase.DrinkUseCase

class DetailViewModel(private val drinkUseCase: DrinkUseCase) : ViewModel() {
    fun setFavoriteDrink(drink: Drink, newState: Boolean) =
        drinkUseCase.setFavoriteDrink(drink, newState)
}