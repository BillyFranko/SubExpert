package com.dicoding.subexpert1billy.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.subexpert1billy.core.data.source.local.entity.FoodEntity

@Database(entities = [FoodEntity::class], version = 1, exportSchema = false)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun FoodDao() : FoodDao
}