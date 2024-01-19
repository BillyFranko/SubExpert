package com.dicoding.subexpert1billy.di

import com.dicoding.subexpert1billy.core.domain.usecase.FoodUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {
    fun foodUseCase() : FoodUseCase
}