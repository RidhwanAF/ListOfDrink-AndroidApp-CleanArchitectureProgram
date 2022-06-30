package com.raf.mydrink.core.domain.usecase

import com.raf.mydrink.core.domain.model.Drink
import com.raf.mydrink.core.domain.repository.IDrinkRepository


class DrinkInteractor(private val drinkRepository: IDrinkRepository): DrinkUseCase {
    override fun getAllDrink() = drinkRepository.getAllDrink()

    override fun getFavoriteDrink() = drinkRepository.getFavoriteDrink()

    override fun setFavoriteDrink(drink: Drink, state: Boolean) = drinkRepository.setFavoriteDrink(drink, state)
}