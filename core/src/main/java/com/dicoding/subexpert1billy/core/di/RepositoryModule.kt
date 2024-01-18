package com.dicoding.subexpert1billy.core.di

import com.dicoding.subexpert1billy.core.data.FoodRepository
import com.dicoding.subexpert1billy.core.domain.repository.IFoodsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(foodRepository: FoodRepository) : IFoodsRepository

}