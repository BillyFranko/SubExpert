package com.dicoding.subexpert1billy.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.subexpert1billy.core.domain.usecase.FoodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (foodUseCase : FoodUseCase) : ViewModel() {
    val food = foodUseCase.getAllFood().asLiveData()
}