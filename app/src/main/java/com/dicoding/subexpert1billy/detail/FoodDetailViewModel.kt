package com.dicoding.subexpert1billy.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.subexpert1billy.core.domain.model.Foods
import com.dicoding.subexpert1billy.core.domain.usecase.FoodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodDetailViewModel @Inject constructor(private val foodUseCase: FoodUseCase) : ViewModel(){
    fun setFavoriteFood(food : Foods, newStats : Boolean){
        viewModelScope.launch {
            foodUseCase.setFavoriteFood(food, newStats)
        }
    }
}