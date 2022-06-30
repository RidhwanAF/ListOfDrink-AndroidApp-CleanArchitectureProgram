package com.raf.mydrink.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListDrinkResponse(
    @field:SerializedName("drinks")
    val drinks: List<DrinkResponse>,
)