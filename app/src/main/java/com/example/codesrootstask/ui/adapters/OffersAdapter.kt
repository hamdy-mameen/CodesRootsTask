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
import com.example.codesrootstask.responses.home.Data
import com.example.codesrootstask.responses.home.DataXXX
import javax.inject.Inject

class OffersAdapter @Inject constructor():RecyclerView.Adapter<OffersAdapter.OffersViewHolder>() {
    inner class OffersViewHolder(val binding: SellItemBinding):RecyclerView.ViewHolder(binding.root)

    val diffUtilCallBack = object :DiffUtil.ItemCallback<Data>(){
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.RestauranthId==newItem.RestauranthId
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem==newItem
        }

    }
    val differ =AsyncListDiffer(this,diffUtilCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffersViewHolder {
        val binding = SellItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return OffersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OffersViewHolder, position: Int) {
       val offer = differ.currentList[position]
        holder.binding.apply {
            Glide.with(root).load(offer.cover).placeholder(R.drawable.ic_launcher_foreground).into(sellCoverImg)
            sellName.text = offer.name
            sellDescription.text = offer.description.toString()
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}