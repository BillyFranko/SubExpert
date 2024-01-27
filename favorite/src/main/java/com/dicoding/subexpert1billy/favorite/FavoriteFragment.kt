package com.dicoding.subexpert1billy.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.subexpert1billy.core.ui.FoodAdapter
import com.dicoding.subexpert1billy.detail.FoodDetail
import com.dicoding.subexpert1billy.di.FavoriteModuleDependencies
import com.dicoding.subexpert1billy.favorite.databinding.FragmentFavoriteBinding
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject


class FavoriteFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val favoriteViewModel: FavoriteViewModel by viewModels {
        factory
    }
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(requireActivity())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireActivity().applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding?.root ?: inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val foodAdapter = FoodAdapter()
            foodAdapter.onItemClick = {
                val intent = Intent(activity, FoodDetail::class.java)
                intent.putExtra(FoodDetail.Data_Food, it)
                startActivity(intent)
            }

            favoriteViewModel.favoriteFood.observe(viewLifecycleOwner) { food ->
                foodAdapter.setData(food)
                if (food.isEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        "There is currently no data available/You haven't picked any favorites yet.",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }

                binding?.let {
                    with(it.rvFavorite) {
                        layoutManager = LinearLayoutManager(context)
                        setHasFixedSize(true)
                        adapter = foodAdapter
                    }
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}