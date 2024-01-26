package com.dicoding.subexpert1billy.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class FoodsResponse(

	@field:SerializedName("meals")
	val meals: List<MealsItem?>? = null
)

data class MealsItem(

	@field:SerializedName("strImageSource")
	val strImageSource: String? = null,

	@field:SerializedName("strCategory")
	val strCategory: String? = null,

	@field:SerializedName("strArea")
	val strArea: String? = null,

	@field:SerializedName("strCreativeCommonsConfirmed")
	val strCreativeCommonsConfirmed: String? = null,

	@field:SerializedName("strTags")
	val strTags: String? = null,

	@field:SerializedName("idMeal")
	val idMeal: String = "",

	@field:SerializedName("strInstructions")
	val strInstructions: String? = null,

	@field:SerializedName("strMealThumb")
	val strMealThumb: String? = null,

	@field:SerializedName("strYoutube")
	val strYoutube: String? = null,

	@field:SerializedName("strMeal")
	val strMeal: String? = null,

	@field:SerializedName("dateModified")
	val dateModified:String? = null,
)
