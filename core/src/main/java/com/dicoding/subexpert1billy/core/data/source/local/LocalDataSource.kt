package com.dicoding.subexpert1billy.core.data.source.local

import com.dicoding.subexpert1billy.core.data.source.local.entity.FoodEntity
import com.dicoding.subexpert1billy.core.data.source.local.room.FoodDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val foodDao: FoodDao){

    fun getAllFoods(): Flow<List<FoodEntity>> = foodDao.getAllFoods()

    fun getAllFavorite(): Flow<List<FoodEntity>> = foodDao.getAllFavorite()

    suspend fun insertFood(food : List<FoodEntity>) = foodDao.insertFood(food)

    fun searchFood(query : String) : Flow<List<FoodEntity>> = foodDao.searchFood(query)

    fun updateFavoriteFood(food : FoodEntity, newState : Boolean) {
        food.isFav = newState
        foodDao.updateFavoriteFood(food)
    }
}