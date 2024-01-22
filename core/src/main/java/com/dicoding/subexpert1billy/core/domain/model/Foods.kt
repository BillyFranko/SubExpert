package com.dicoding.subexpert1billy.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Foods (
    val idMeal: String? = null,
    val strImageSource: String? = null,
    val strCategory: String? = null,
    val strArea: String? = null,
    val strCreativeCommonsConfirmed: String? = null,
    val strTags: String? = null,
    val strInstructions: String? = null,
    val strMealThumb: String? = null,
    val strYoutube: String? = null,
    val strMeal: String? = null,
    val dateModified: String? = null,
    var isFav: Boolean = false,
) : Parcelable