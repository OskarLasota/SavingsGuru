package com.frezzcoding.savingsguru.functionalities.graphs.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.frezzcoding.savingsguru.R
import com.frezzcoding.savingsguru.data.models.EstimatedSavings
import com.frezzcoding.savingsguru.databinding.ItemEstimatedSavingsBinding

class GraphViewHolder(
    val binding: ItemEstimatedSavingsBinding,
    val listener: GraphsAdapter.OnClickListenerSavings
) : RecyclerView.ViewHolder(binding.root) {

    private var savingsAmount = 0
    private lateinit var estimatedSavings : EstimatedSavings
    private var lastEntryConfirmed = false

    fun bindTo(estimatedSavings: EstimatedSavings, position: Int) {
        this.estimatedSavings = estimatedSavings
        binding.estimatedSavings = estimatedSavings
        println("here")
        println(estimatedSavings.toString())
        setListeners(position)
        initializeItem()
    }

    private fun initializeItem(){
        binding.etSavingsAmount.text?.clear()
        binding.etSavingsAmount.text?.insert(0,if(estimatedSavings.id == 0 ) "" else estimatedSavings.amount.toString())
        if(estimatedSavings.lastEntry){
            binding.tilSavingsAmount.startIconDrawable =
                ContextCompat.getDrawable(binding.root.context, R.drawable.ic_new)
        }else{
            binding.tilSavingsAmount.startIconDrawable =
                ContextCompat.getDrawable(binding.root.context, R.drawable.ic_money)
        }
    }

    private fun setListeners(position : Int){
        binding.tvConfirm.setOnClickListener {
            if(estimatedSavings.lastEntry){
                listener.confirmSavings(savingsAmount, position)
                binding.etSavingsAmount.text?.clear()
            }else{
                estimatedSavings.amount = savingsAmount
                listener.updateSavings(estimatedSavings)
            }
            binding.tvConfirm.visibility = View.GONE
            binding.tilSavingsAmount.startIconDrawable =
                ContextCompat.getDrawable(binding.root.context, R.drawable.ic_money)
            lastEntryConfirmed = true
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