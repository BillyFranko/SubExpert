package com.dicoding.subexpert1billy.favorite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.subexpert1billy.R
import com.dicoding.subexpert1billy.core.data.Resource
import com.dicoding.subexpert1billy.core.ui.FoodAdapter
import com.dicoding.subexpert1billy.detail.FoodDetail
import com.dicoding.subexpert1billy.di.FavoriteModuleDependencies
import com.dicoding.subexpert1billy.favorite.databinding.FragmentFavoriteBinding
import com.dicoding.subexpert1billy.home.HomeViewModel
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject


class FavoriteFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val favoriteViewModel: FavoriteViewModel by viewModels {
        factory
    }
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

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

//    override fun onAttach(context: Context) {
//        DaggerFavoriteComponent.builder()
//            .context(requireActivity())
//            .appDependencies(
//                EntryPointAccessors.fromApplication(
//                    requireActivity().applicationContext,
//                    FavoriteModuleDependencies::class.java
//                )
//            )
//            .build()
//            .inject(this)
//        super.onAttach(context)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
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
                if (food.isEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        "Something gone wrong. Please try again",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else {
                    foodAdapter.setData(food)
                    binding.progressBar.visibility = View.GONE
                }

                with(binding.rvFavorite) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = foodAdapter
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}