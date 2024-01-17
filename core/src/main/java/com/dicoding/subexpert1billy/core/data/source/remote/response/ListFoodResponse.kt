package com.dicoding.subexpert1billy.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListFoodResponse (

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("foods")
    val foods: List<FoodsResponse>

)