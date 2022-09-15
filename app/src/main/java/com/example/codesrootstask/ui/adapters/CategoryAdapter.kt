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
import com.example.codesrootstask.responses.ads.AdsSpacesprice
import com.example.codesrootstask.responses.ads.Sliders
import com.example.codesrootstask.responses.category.CategoryResponseItem
import javax.inject.Inject

class CategoryAdapter @Inject constructor():RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    inner class CategoryViewHolder(val binding: CategoryItemBinding):RecyclerView.ViewHolder(binding.root)

    val diffUtilCallBack = object :DiffUtil.ItemCallback<CategoryResponseItem>(){
        override fun areItemsTheSame(oldItem: CategoryResponseItem, newItem: CategoryResponseItem): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: CategoryResponseItem, newItem: CategoryResponseItem): Boolean {
            return oldItem==newItem
        }

    }
    val differ =AsyncListDiffer(this,diffUtilCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
       val category = differ.currentList[position]
        holder.binding.apply {
            categoryTitle.text = category.name
            Glide.with(root).load(category.photo).placeholder(R.drawable.ic_launcher_foreground).into(categoryImage)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}