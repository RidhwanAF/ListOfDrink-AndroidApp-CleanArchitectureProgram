package com.raf.mydrink

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.raf.mydrink.core.domain.usecase.DrinkUseCase

class MainViewModel(drinkUseCase: DrinkUseCase) : ViewModel() {
    val drink = drinkUseCase.getAllDrink().asLiveData()
}