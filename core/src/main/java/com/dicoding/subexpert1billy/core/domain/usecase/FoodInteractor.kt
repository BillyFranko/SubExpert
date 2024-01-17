package com.dicoding.subexpert1billy.core.domain.usecase

import com.dicoding.subexpert1billy.core.data.FoodRepository
import com.dicoding.subexpert1billy.core.data.Resource
import com.dicoding.subexpert1billy.core.domain.model.Foods
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FoodInteractor @Inject constructor(private val foodRepository: FoodRepository) : FoodUseCase{
    override fun getAllFood(): Flow<Resource<List<Foods>>> {
        TODO("Not yet implemented")
    }

    override fun getFavoriteFoods(): Flow<List<Foods>> {
        TODO("Not yet implemented")
    }

    override fun setFavoriteFood(food: Foods, state: Boolean) {
        TODO("Not yet implemented")
    }

}