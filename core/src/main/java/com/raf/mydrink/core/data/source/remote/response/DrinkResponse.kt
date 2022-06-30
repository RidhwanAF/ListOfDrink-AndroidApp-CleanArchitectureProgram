package com.raf.mydrink.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DrinkResponse(

    @field:SerializedName("idDrink")
    val drinkId: String,

    @field:SerializedName("strDrink")
    val drinkName: String,

    @field:SerializedName("strInstructions")
    val drinkInstruction: String,

    @field:SerializedName("strCategory")
    val drinkCategory: String,

    @field:SerializedName("strDrinkThumb")
    val drinkImg: String
)