package com.dicoding.subexpert1billy.core.domain.repository

import com.dicoding.subexpert1billy.core.data.Resource
import com.dicoding.subexpert1billy.core.domain.model.Foods
import kotlinx.coroutines.flow.Flow

interface IFoodsRepository {

    fun getAllFoods() : Flow<Resource<List<Foods>>>

    fun getFavouriteFoods() : Flow<List<Foods>>

    fun setFavouriteFoods(foods : Foods, state : Boolean)
}