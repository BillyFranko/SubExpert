package com.dicoding.subexpert1billy.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dicoding.subexpert1billy.core.data.source.local.entity.FoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {

    @Query("SELECT * FROM foods")
    fun getAllFoods(): Flow<List<FoodEntity>>

    @Query("SELECT * FROM foods where isFavorite = 1")
    fun getAllFavorite(): Flow<List<FoodEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFood(food : List<FoodEntity>)

    @Update
    fun updateFavoriteFood(food : FoodEntity)

}