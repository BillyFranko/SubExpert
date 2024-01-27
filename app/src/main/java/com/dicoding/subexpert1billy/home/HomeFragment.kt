package com.dicoding.subexpert1billy.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.subexpert1billy.R
import com.dicoding.subexpert1billy.core.data.Resource
import com.dicoding.subexpert1billy.core.ui.FoodAdapter
import com.dicoding.subexpert1billy.databinding.FragmentHomeBinding
import com.dicoding.subexpert1billy.detail.FoodDetail
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    private var isSearchResults: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root ?: inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        if (activity != null) {
            val foodAdapter = FoodAdapter()
            foodAdapter.onItemClick = {
                val intent = Intent(activity, FoodDetail::class.java)
                intent.putExtra(FoodDetail.Data_Food, it)
                startActivity(intent)
            }


            homeViewModel.food.observe(viewLifecycleOwner) { food ->
                if (food != null && !isSearchResults) {
                    when (food) {
                        is Resource.Loading -> binding?.progressBar?.visibility = View.VISIBLE
                        is Resource.Error -> {
                            binding?.progressBar?.visibility = View.GONE
                            Toast.makeText(
                                requireContext(),
                                "Something gone wrong. Please try again",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }

                        is Resource.Success -> {
                            binding?.progressBar?.visibility = View.GONE
                            foodAdapter.setData(food.data)
                            Log.d("Food", "Updating adapter with Food results")
                        }
                    }
                }
            }


            binding?.searchBar?.hint = "Search for foods"
            with(binding) {
                this?.searchView?.setupWithSearchBar(searchBar)
                this?.searchView
                    ?.editText
                    ?.setOnEditorActionListener { textView, actionId, event ->
                        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                            searchBar.setText(searchView.text)
                            searchView.hide()
                            isSearchResults = true
                            homeViewModel.setSearchQuery(searchBar.text.toString())
                            return@setOnEditorActionListener true
                        }
                        false
                    }
            }

            homeViewModel.search.observe(viewLifecycleOwner) {
                if (isSearchResults) {
                    foodAdapter.setData(it)
                    Log.d("SearchObserver", "Updating adapter with search results")
                    isSearchResults = false
                }
            }


            binding?.let {
                with(it.rvFood) {
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