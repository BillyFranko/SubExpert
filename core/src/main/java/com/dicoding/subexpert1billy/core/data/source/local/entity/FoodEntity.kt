package com.dicoding.subexpert1billy.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "foods")
data class FoodEntity (
    @PrimaryKey
    @ColumnInfo(name = "idMeal")
    val idMeal: String? = null,

    @ColumnInfo(name = "strImageSource")
    val strImageSource: Any? = null,

    @ColumnInfo(name = "strCategory")
    val strCategory: String? = null,

    @ColumnInfo(name = "strArea")
    val strArea: String? = null,

    @ColumnInfo(name = "strCreativeCommonsConfirmed")
    val strCreativeCommonsConfirmed: Any? = null,

    @ColumnInfo(name = "strTags")
    val strTags: String? = null,

    @ColumnInfo(name = "strInstructions")
    val strInstructions: String? = null,

    @ColumnInfo(name = "strMealThumb")
    val strMealThumb: String? = null,

    @ColumnInfo(name = "strYoutube")
    val strYoutube: String? = null,

    @ColumnInfo(name = "strMeal")
    val strMeal: String? = null,

    @ColumnInfo(name = "dateModified")
    val dateModified: Any? = null,

    @ColumnInfo(name = "isFavorite")
    var isFav: Boolean = false,
)