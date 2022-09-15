package com.example.codesrootstask.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.codesrootstask.R
import com.example.codesrootstask.databinding.AdsItemBinding
import com.example.codesrootstask.databinding.CategoryItemBinding
import com.example.codesrootstask.databinding.RestaurantsItemBinding
import com.example.codesrootstask.responses.ads.AdsSpacesprice
import com.example.codesrootstask.responses.ads.Sliders
import com.example.codesrootstask.responses.category.CategoryResponseItem
import com.example.codesrootstask.responses.home.Data
import javax.inject.Inject

class RestaurantsAdapter @Inject constructor():RecyclerView.Adapter<RestaurantsAdapter.RestaurantsViewHolder>() {
    inner class RestaurantsViewHolder(val binding: RestaurantsItemBinding):RecyclerView.ViewHolder(binding.root)

    val diffUtilCallBack = object :DiffUtil.ItemCallback<Data>(){
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.RestauranthId==newItem.RestauranthId
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem==newItem
        }

    }
    val differ =AsyncListDiffer(this,diffUtilCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantsViewHolder {
        val binding = RestaurantsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RestaurantsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantsViewHolder, position: Int) {
       val restaurant = differ.currentList[position]
        holder.binding.apply {
            Glide.with(root).load(restaurant.cover).placeholder(R.drawable.ic_launcher_foreground).into(restaurantCoverImg)
            Glide.with(root).load(restaurant.logo).placeholder(R.drawable.ic_launcher_foreground).into(restaurantLogoImg)
            restaurantName.text = restaurant.name
            if(restaurant.IsOpen == "false"){
                restaurantState.text ="مغلق"
            }else{
                restaurantState.text ="مفتوح"
            }

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}