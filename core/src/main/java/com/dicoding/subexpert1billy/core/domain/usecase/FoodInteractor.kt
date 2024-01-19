package com.dicoding.subexpert1billy.core.domain.usecase

import com.dicoding.subexpert1billy.core.data.FoodRepository
import com.dicoding.subexpert1billy.core.data.Resource
import com.dicoding.subexpert1billy.core.domain.model.Foods
import com.dicoding.subexpert1billy.core.domain.repository.IFoodsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FoodInteractor @Inject constructor(private val foodRepository: IFoodsRepository) : FoodUseCase{
    override fun getAllFood(): Flow<Resource<List<Foods>>> = foodRepository.getAllFoods()


    override fun getFavoriteFoods(): Flow<List<Foods>> = foodRepository.getFavouriteFoods()


    override fun setFavoriteFood(food: Foods, state: Boolean) = foodRepository.setFavouriteFoods(food, state)

}