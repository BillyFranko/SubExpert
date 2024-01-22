package com.dicoding.subexpert1billy.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.dicoding.subexpert1billy.core.domain.usecase.FoodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (foodUseCase : FoodUseCase) : ViewModel() {
    val food = foodUseCase.getAllFood().asLiveData()
    private val _searchQuery = MutableLiveData<String>()
    private val searchQuery : LiveData<String>
        get() = _searchQuery

    fun setSearchQuery(query: String) {
        if (_searchQuery.value != query) {
            _searchQuery.value = query
        }
    }

    val search = searchQuery.switchMap { query->
        foodUseCase.searchFood(query).asLiveData()
    }
}