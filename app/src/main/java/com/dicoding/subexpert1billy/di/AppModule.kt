package com.dicoding.subexpert1billy.di

import com.dicoding.subexpert1billy.core.domain.usecase.FoodInteractor
import com.dicoding.subexpert1billy.core.domain.usecase.FoodUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun provideUseCase(foodInteractor: FoodInteractor) : FoodUseCase
}