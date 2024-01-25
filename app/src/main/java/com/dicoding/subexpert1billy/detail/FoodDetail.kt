package com.dicoding.subexpert1billy.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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

        val detailFood = intent.getParcelableExtra<Foods>(Data_Food)
        showDetailFood(detailFood)

        val favStatus  = detailFood?.isFav
        Toast.makeText(this, "$favStatus", Toast.LENGTH_SHORT).show()
    }

    private fun showDetailFood(detailFood: Foods?) {
        detailFood?.let {
            Glide.with(this)
                .load(detailFood.strMealThumb)
                .into(binding.ivThumbnail)
            binding.tvFoodName.text = detailFood.strMeal
            binding.tvArea.text = detailFood.strArea
            binding.tvCategory.text = detailFood.strCategory
            binding.tvInstructions.text = getString(R.string.how_to_cook) +  detailFood.strInstructions
            setStatus(detailFood.isFav)
            binding.btnFav.setOnClickListener {
                var favorite = detailFood.isFav
                favorite = !favorite
                foodDetailViewModel.setFavoriteFood(detailFood, favorite)
                setStatus(favorite)
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

    companion object{
        const val Data_Food = "Food_Data"
    }
}