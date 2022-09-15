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
import com.example.codesrootstask.databinding.SellItemBinding
import com.example.codesrootstask.responses.ads.AdsSpacesprice
import com.example.codesrootstask.responses.ads.Sliders
import com.example.codesrootstask.responses.category.CategoryResponseItem
import com.example.codesrootstask.responses.home.DataXXX
import javax.inject.Inject

class DishesAdapter @Inject constructor():RecyclerView.Adapter<DishesAdapter.DishesViewHolder>() {
    inner class DishesViewHolder(val binding: SellItemBinding):RecyclerView.ViewHolder(binding.root)

    val diffUtilCallBack = object :DiffUtil.ItemCallback<DataXXX>(){
        override fun areItemsTheSame(oldItem: DataXXX, newItem: DataXXX): Boolean {
            return oldItem.menu_categories_items.id==newItem.menu_categories_items.id
        }

        override fun areContentsTheSame(oldItem: DataXXX, newItem: DataXXX): Boolean {
            return oldItem.menu_categories_items==newItem.menu_categories_items
        }

    }
    val differ =AsyncListDiffer(this,diffUtilCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishesViewHolder {
        val binding = SellItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DishesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DishesViewHolder, position: Int) {
       val dish = differ.currentList[position]
        holder.binding.apply {
            Glide.with(root).load(dish.menu_categories_items.photo).placeholder(R.drawable.ic_launcher_foreground).into(sellCoverImg)
            sellName.text = dish.menu_categories_items.name
            sellDescription.text = dish.menu_categories_items.descriptions
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}