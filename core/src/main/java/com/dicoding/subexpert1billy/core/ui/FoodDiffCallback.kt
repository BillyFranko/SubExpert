package com.dicoding.subexpert1billy.core.ui

import androidx.recyclerview.widget.DiffUtil
import com.dicoding.subexpert1billy.core.domain.model.Foods

class FoodDiffCallback (private val oldFoodList : List<Foods>, private val newFoodList: List<Foods>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldFoodList.size
    }

    override fun getNewListSize(): Int {
        return newFoodList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldFoodList[oldItemPosition].idMeal == newFoodList[newItemPosition].idMeal
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldFoodList[oldItemPosition] == newFoodList[newItemPosition]
    }
}