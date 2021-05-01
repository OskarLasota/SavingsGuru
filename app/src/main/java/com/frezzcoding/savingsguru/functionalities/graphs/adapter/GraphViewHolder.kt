package com.frezzcoding.savingsguru.functionalities.graphs.adapter

import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.frezzcoding.savingsguru.data.models.EstimatedSavings
import com.frezzcoding.savingsguru.databinding.ItemEstimatedSavingsBinding

class GraphViewHolder(
    val binding: ItemEstimatedSavingsBinding,
    val listener: GraphsAdapter.OnClickListenerSavings
) : RecyclerView.ViewHolder(binding.root) {

    private var savingsAmount = 0

    fun bindTo(estimatedSavings: EstimatedSavings, position: Int) {
        binding.estimatedSavings = estimatedSavings
        binding.tvAddAnother.setOnClickListener {
            listener.addAnotherClick(estimatedSavings.id, position)
        }
        binding.tvConfirm.setOnClickListener {
            listener.confirmSavings(savingsAmount)
            binding.tvConfirm.visibility = View.GONE
        }
        binding.etSavingsAmount.doOnTextChanged { text, _, _, count ->
            if (text.toString().isEmpty()) {
                savingsAmount = 0
                binding.tvConfirm.visibility = View.GONE
            } else {
                savingsAmount = binding.etSavingsAmount.text.toString().toInt()
                binding.tvConfirm.visibility = View.VISIBLE
            }
        }
    }
}