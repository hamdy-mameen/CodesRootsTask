package com.example.codesrootstask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.codesrootstask.data.Resource
import com.example.codesrootstask.databinding.ActivityMainBinding
import com.example.codesrootstask.ui.adapters.*
import com.example.codesrootstask.viewModels.MainViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    private val viewModel:MainViewModel by viewModels()
    @Inject
    lateinit var adsAdapter:AdsPagerAdapter
    @Inject
    lateinit var categoryAdapter: CategoryAdapter
    @Inject
    lateinit var offersAdapter: OffersAdapter
    @Inject
    lateinit var dishesAdapter: DishesAdapter
    @Inject
    lateinit var restaurantsAdapter: RestaurantsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpAdsPager()
        setUpCategoryRecyclerView()
        setUpDishesRecyclerView()
        setUpOfferRecyclerView()
        setUpRestaurantRecyclerView()
        viewModel.adsLiveData.observe(this, Observer { resources->
            when(resources){
                is Resource.Loading-> Log.d("TAG","ads loading...")
                is Resource.Success ->{
                    resources.data?.let {
                        adsAdapter.differ.submitList(it[0].AdsSpacesprice)
                    }
                }
                is Resource.Failure->{
                    resources.message?.let {
                        Toast.makeText(this, "Ads Error $it", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        })
        viewModel.categoryLiveData.observe(this, Observer { resources->
            when(resources){
                is Resource.Loading-> Log.d("TAG","category loading...")
                is Resource.Success ->{
                    resources.data?.let {
                        categoryAdapter.differ.submitList(it)
                    }
                }
                is Resource.Failure->{
                    resources.message?.let {
                        Toast.makeText(this, "Category Error $it", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
        viewModel.mainLiveData.observe(this, Observer { resources->
            when(resources){
                is Resource.Loading-> Log.d("TAG","main loading...")
                is Resource.Success ->{
                    resources.data?.let {
                       offersAdapter.differ.submitList(it.lastoffers.data)
                       dishesAdapter.differ.submitList(it.MostSellItems.data)
                       restaurantsAdapter.differ.submitList(it.GetNearestBranche.data)
                    }
                }
                is Resource.Failure->{
                    resources.message?.let {
                        Toast.makeText(this, "main Error $it", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun setUpAdsPager(){
        binding.adsPager.apply {
            adapter =adsAdapter
            binding.tabLayout.apply {
                TabLayoutMediator(this,binding.adsPager){_,_->
                }.attach()
            }
        }
    }

    private fun setUpCategoryRecyclerView(){
        binding.categoryRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity,RecyclerView.HORIZONTAL,false)
            adapter = categoryAdapter
        }
    }
    private fun setUpDishesRecyclerView(){
        binding.mostOrderedRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity,RecyclerView.HORIZONTAL,false)
            adapter = dishesAdapter
        }
    }
    private fun setUpRestaurantRecyclerView(){
        binding.restRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity,RecyclerView.HORIZONTAL,false)
            adapter = restaurantsAdapter
        }
    }
    private fun setUpOfferRecyclerView(){
        binding.offersRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity,RecyclerView.HORIZONTAL,false)
            adapter = offersAdapter
        }
    }
}