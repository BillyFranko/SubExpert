package com.dicoding.subexpert1billy.core.domain.usecase

import com.dicoding.subexpert1billy.core.data.Resource
import com.dicoding.subexpert1billy.core.domain.model.Foods
import kotlinx.coroutines.flow.Flow


interface FoodUseCase {
    fun getAllFood(): Flow<Resource<List<Foods>>>
    fun getFavoriteFoods(): Flow<List<Foods>>
    fun setFavoriteFood(food : Foods, state : Boolean)
    fun searchFood(query : String) : Flow<List<Foods>>
}