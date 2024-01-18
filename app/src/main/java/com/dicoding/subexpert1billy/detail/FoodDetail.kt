package com.dicoding.subexpert1billy.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.dicoding.subexpert1billy.R
import com.dicoding.subexpert1billy.core.domain.model.Foods
import com.dicoding.subexpert1billy.databinding.ActivityFoodDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodDetail : AppCompatActivity() {

    private lateinit var binding : ActivityFoodDetailBinding
    private val foodDetailViewModel : FoodDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val detailFood = intent.getParcelableExtra<Foods>("Data_Detail")
        showDetailFood(detailFood)
    }

    private fun showDetailFood(detailFood: Foods?) {
        detailFood?.let {
            Glide.with(this)
                .load(detailFood.strMealThumb)
                .into(binding.ivThumbnail)
            binding.tvFoodName.text = detailFood.strMeal
            binding.tvArea.text = detailFood.strArea
            binding.tvCategory.text = detailFood.strCategory
            binding.tvInstructions.text = detailFood.strInstructions

            var favorite = detailFood.isFav
            setStatus(favorite)
            binding.btnFav.setOnClickListener {
                foodDetailViewModel.setFavoriteFood(detailFood, !favorite)
                setStatus(!favorite)
            }
        }
    }

    private fun setStatus(favorite: Boolean) {
        if (favorite){
            binding.btnFav.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.favoriteputih))
        }else{
            binding.btnFav.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.favorite))
        }
    }
}