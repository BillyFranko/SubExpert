package com.dicoding.subexpert1billy.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.subexpert1billy.core.data.Resource
import com.dicoding.subexpert1billy.core.ui.FoodAdapter
import com.dicoding.subexpert1billy.databinding.FragmentHomeBinding
import com.dicoding.subexpert1billy.detail.FoodDetail
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel : HomeViewModel by viewModels()
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity!=null) {
            val foodAdapter = FoodAdapter()
            foodAdapter.onItemClick = {
                val intent = Intent(activity, FoodDetail::class.java)
                intent.putExtra(FoodDetail.Data_Food, it)
                startActivity(intent)
            }


            homeViewModel.food.observe(viewLifecycleOwner) { food ->
                if (food != null) {
                    when (food) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(
                                requireContext(),
                                "Something gone wrong. Please try again",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }

                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            foodAdapter.setData(food.data)
                        }
                    }
                }
            }

            with(binding.rvFood){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = foodAdapter
            }
        }
    }

    override fun onDestroyView(){
        super.onDestroyView()
        _binding = null
    }


}