package com.dicoding.subexpert1billy.core.utils

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.dicoding.subexpert1billy.core.data.source.local.entity.FoodEntity
import com.dicoding.subexpert1billy.core.data.source.remote.response.FoodsResponse
import com.dicoding.subexpert1billy.core.data.source.remote.response.MealsItem
import com.dicoding.subexpert1billy.core.domain.model.Foods

object DataMapper {
    fun mapResponsesToEntities(input: List<MealsItem?>?): List<FoodEntity> {
        val foodList = ArrayList<FoodEntity>()
        input?.map {
            val food = FoodEntity(
                idMeal = it?.idMeal,
                strImageSource = it?.strImageSource,
                strCategory = it?.strCategory,
                strArea = it?.strArea,
                strCreativeCommonsConfirmed = it?.strCreativeCommonsConfirmed,
                strTags = it?.strTags,
                strInstructions = it?.strInstructions,
                strMealThumb = it?.strMealThumb,
                strYoutube = it?.strYoutube,
                strMeal = it?.strMeal,
                dateModified = it?.dateModified,
                isFav = false
            )
            foodList.add(food)
        }
//        input.forEach{ it ->
//            it.meals.let { meals ->
//                meals?.map {
//                    val food = FoodEntity(
//                        idMeal = it?.idMeal,
//                        strImageSource = it?.strImageSource,
//                        strCategory = it?.strCategory,
//                        strArea = it?.strArea,
//                        strCreativeCommonsConfirmed = it?.strCreativeCommonsConfirmed,
//                        strTags = it?.strTags,
//                        strInstructions = it?.strInstructions,
//                        strMealThumb = it?.strMealThumb,
//                        strYoutube = it?.strYoutube,
//                        strMeal = it?.strMeal,
//                        dateModified = it?.dateModified,
//                        isFav = false
//                    )
//                    foodList.add(food)
//                }
//            }
//        }
        return foodList
    }

    fun mapEntitiesToDomain(input: List<FoodEntity>): List<Foods> =
        input.map {
            Foods(
                idMeal = it.idMeal,
                strImageSource = it.strImageSource.toString(),
                strCategory = it.strCategory,
                strArea = it.strArea,
                strCreativeCommonsConfirmed = it.strCreativeCommonsConfirmed.toString(),
                strTags = it.strTags,
                strInstructions = it.strInstructions,
                strMealThumb = it.strMealThumb,
                strYoutube = it.strYoutube,
                strMeal = it.strMeal,
                dateModified = it.dateModified.toString(),
                isFav = false
            )
        }

    fun mapDomainToEntity(input: Foods) = FoodEntity(
        idMeal = input.idMeal,
        strImageSource = input.strImageSource.toString(),
        strCategory = input.strCategory,
        strArea = input.strArea,
        strCreativeCommonsConfirmed = input.strCreativeCommonsConfirmed.toString(),
        strTags = input.strTags,
        strInstructions = input.strInstructions,
        strMealThumb = input.strMealThumb,
        strYoutube = input.strYoutube,
        strMeal = input.strMeal,
        dateModified = input.dateModified.toString(),
        isFav = input.isFav
    )
}