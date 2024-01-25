package com.dicoding.subexpert1billy.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.subexpert1billy.core.R
import com.dicoding.subexpert1billy.core.databinding.ItemListFoodBinding
import com.dicoding.subexpert1billy.core.domain.model.Foods

class FoodAdapter : RecyclerView.Adapter<FoodAdapter.ListViewHolder>() {

    private var listData = ArrayList<Foods>()
    var onItemClick : ((Foods) -> Unit)? = null


    fun setData(newListData: List<Foods>?){
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }


    inner class ListViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListFoodBinding.bind(itemView)
        fun bind(data : Foods){
            with(binding){
                Glide.with(itemView.context)
                    .load(data.strMealThumb)
                    .into(ivFood)
                tvName.text = data.strMeal
                tvArea.text = data.strArea
                tvCategory.text = data.strCategory
            }
        }
        init {
            binding.root.setOnClickListener{
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_food, parent, false))

    override fun getItemCount() = listData.size


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }
}