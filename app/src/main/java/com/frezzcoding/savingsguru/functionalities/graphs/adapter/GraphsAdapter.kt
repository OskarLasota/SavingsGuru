package com.frezzcoding.savingsguru.functionalities.graphs.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.frezzcoding.savingsguru.R
import com.frezzcoding.savingsguru.data.models.EstimatedSavings

class GraphsAdapter(private val listener : OnClickListenerSavings) : ListAdapter<EstimatedSavings, GraphViewHolder>(GraphsDiffUtil()) {

    class GraphsDiffUtil : DiffUtil.ItemCallback<EstimatedSavings>() {
        override fun areItemsTheSame(oldItem: EstimatedSavings, newItem: EstimatedSavings): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: EstimatedSavings, newItem: EstimatedSavings): Boolean {
            return oldItem.id == newItem.id //check content too
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GraphViewHolder {
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        return GraphViewHolder(DataBindingUtil.inflate(inflater, R.layout.item_estimated_savings, parent, false), listener)
    }

    override fun onBindViewHolder(holder: GraphViewHolder, position: Int) {
        getItem(position).let {
            holder.bindTo(it, position)
        }
    }

    interface OnClickListenerSavings{
        fun addAnotherClick(id : Int, position : Int)
        fun confirmSavings(amount: Int)
        fun notifyError(message : String)
    }


}