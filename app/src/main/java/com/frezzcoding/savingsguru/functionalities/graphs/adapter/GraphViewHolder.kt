package com.frezzcoding.savingsguru.functionalities.graphs.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.frezzcoding.savingsguru.R
import com.frezzcoding.savingsguru.data.models.EstimatedSavings
import com.frezzcoding.savingsguru.databinding.ItemEstimatedSavingsBinding

class GraphViewHolder(val binding : ItemEstimatedSavingsBinding, val listener : GraphsAdapter.OnClickListenerSavings) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(estimatedSavings: EstimatedSavings, position : Int){
        binding.estimatedSavings = estimatedSavings
        binding.tvAddAnother.setOnClickListener {
            listener.addAnotherClick(estimatedSavings.id, position)
        }
        binding.tvConfirm.setOnClickListener {
            listener.confirmSavings(it.id)
            binding.tvConfirm.visibility = View.GONE
        }
        binding.etSavingsAmount.doOnTextChanged { text, _, _, count ->
            if(text.toString().isEmpty()){
                binding.tvConfirm.background = ContextCompat.getDrawable(binding.root.context, R.color.dark_grey)
            }else{
                binding.tvConfirm.background = ContextCompat.getDrawable(binding.root.context, R.color.blue)
            }
            binding.tvConfirm.visibility = View.VISIBLE
        }
    }
}