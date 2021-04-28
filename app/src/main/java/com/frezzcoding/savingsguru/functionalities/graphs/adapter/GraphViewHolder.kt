package com.frezzcoding.savingsguru.functionalities.graphs.adapter

import androidx.recyclerview.widget.RecyclerView
import com.frezzcoding.savingsguru.data.models.EstimatedSavings
import com.frezzcoding.savingsguru.databinding.ItemEstimatedSavingsBinding

class GraphViewHolder(val binding : ItemEstimatedSavingsBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(estimatedSavings: EstimatedSavings){
        binding.estimatedSavings = estimatedSavings
    }

}