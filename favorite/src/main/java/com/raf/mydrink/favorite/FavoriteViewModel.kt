package com.raf.mydrink.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.raf.mydrink.core.domain.usecase.DrinkUseCase

class FavoriteViewModel(drinkUseCase: DrinkUseCase) : ViewModel() {
    val favoriteDrink = drinkUseCase.getFavoriteDrink().asLiveData()
}