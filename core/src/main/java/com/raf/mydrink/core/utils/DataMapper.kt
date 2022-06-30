package com.raf.mydrink.core.utils

import com.raf.mydrink.core.data.source.local.entity.DrinkEntity
import com.raf.mydrink.core.data.source.remote.response.DrinkResponse
import com.raf.mydrink.core.domain.model.Drink

object DataMapper {
    fun mapResponsesToEntities(input: List<DrinkResponse>): List<DrinkEntity> {
        val drinkList = ArrayList<DrinkEntity>()
        input.map {
            val drink = DrinkEntity(
                drinkId = it.drinkId,
                drinkName = it.drinkName,
                drinkInstructor = it.drinkInstruction,
                drinkCategory = it.drinkCategory,
                drinkImg = it.drinkImg,
                isFavorite = false
            )
            drinkList.add(drink)
        }
        return drinkList
    }

    fun mapEntitiesToDomain(input: List<DrinkEntity>): List<Drink> =
        input.map {
            Drink(
                drinkId = it.drinkId,
                drinkName = it.drinkName,
                drinkInstruction = it.drinkInstructor,
                drinkCategory = it.drinkCategory,
                drinkImg = it.drinkImg,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Drink) = DrinkEntity(
        drinkId = input.drinkId,
        drinkName = input.drinkName,
        drinkInstructor = input.drinkInstruction,
        drinkCategory = input.drinkCategory,
        drinkImg = input.drinkImg,
        isFavorite = input.isFavorite
    )
}