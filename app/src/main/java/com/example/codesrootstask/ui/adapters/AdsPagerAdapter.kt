package com.example.codesrootstask.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.codesrootstask.R
import com.example.codesrootstask.databinding.AdsItemBinding
import com.example.codesrootstask.responses.ads.AdsSpacesprice
import com.example.codesrootstask.responses.ads.Sliders
import javax.inject.Inject

class AdsPagerAdapter @Inject constructor():RecyclerView.Adapter<AdsPagerAdapter.AdsViewHolder>() {
    inner class AdsViewHolder(val binding: AdsItemBinding):RecyclerView.ViewHolder(binding.root)

    val diffUtilCallBack = object :DiffUtil.ItemCallback<AdsSpacesprice>(){
        override fun areItemsTheSame(oldItem: AdsSpacesprice, newItem: AdsSpacesprice): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: AdsSpacesprice, newItem: AdsSpacesprice): Boolean {
            return oldItem==newItem
        }

    }
    val differ =AsyncListDiffer(this,diffUtilCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdsViewHolder {
        val binding = AdsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AdsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdsViewHolder, position: Int) {
       val slider = differ.currentList[position]
        holder.binding.apply {
            Glide.with(root).load(slider.sliders.photo).placeholder(R.drawable.ic_launcher_foreground).into(adsImage)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}