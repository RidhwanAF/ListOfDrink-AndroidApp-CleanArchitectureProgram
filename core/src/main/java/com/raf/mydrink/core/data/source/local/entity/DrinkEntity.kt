package com.raf.mydrink.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drink")
data class DrinkEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "drinkId")
    var drinkId: String,

    @ColumnInfo(name = "drinkName")
    var drinkName: String,

    @ColumnInfo(name = "drinkInstructor")
    var drinkInstructor: String,

    @ColumnInfo(name = "drinkCategory")
    var drinkCategory: String,

    @ColumnInfo(name = "drinkImg")
    var drinkImg: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false,
)