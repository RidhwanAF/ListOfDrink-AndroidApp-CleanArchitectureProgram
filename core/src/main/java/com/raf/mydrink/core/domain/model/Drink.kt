package com.raf.mydrink.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Drink (
    val drinkId: String,
    val drinkName: String,
    val drinkInstruction: String,
    val drinkCategory: String,
    val drinkImg: String,
    val isFavorite: Boolean
) : Parcelable