package com.dicoding.subexpert1billy.core.di

import android.content.Context
import androidx.room.Room
import com.dicoding.subexpert1billy.core.data.source.local.room.FoodDao
import com.dicoding.subexpert1billy.core.data.source.local.room.FoodDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : FoodDatabase = Room.databaseBuilder(
        context,
        FoodDatabase::class.java, "Food.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideFoodDao(database: FoodDatabase) : FoodDao = database.FoodDao()

}