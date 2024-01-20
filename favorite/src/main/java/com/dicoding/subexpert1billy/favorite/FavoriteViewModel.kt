package com.dicoding.subexpert1billy.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.subexpert1billy.core.domain.usecase.FoodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor (foodUseCase : FoodUseCase) : ViewModel() {
    val favoriteFood = foodUseCase.getFavoriteFoods().asLiveData()
}