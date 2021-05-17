package com.frezzcoding.savingsguru.functionalities.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.frezzcoding.savingsguru.R
import com.frezzcoding.savingsguru.data.models.Scenario
import com.frezzcoding.savingsguru.databinding.ItemScenarioBinding

class ScenarioAdapter(val listener : ScenarioClickListener) : ListAdapter<Scenario, ScenarioViewHolder>(ScenarioDiffUtil()) {

    private lateinit var binding : ItemScenarioBinding

    class ScenarioDiffUtil : DiffUtil.ItemCallback<Scenario>() {
        override fun areItemsTheSame(oldItem: Scenario, newItem: Scenario): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Scenario, newItem: Scenario): Boolean {
            return oldItem.id == newItem.id //check content too
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScenarioViewHolder {
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(inflater, R.layout.item_scenario, parent, false)

        return ScenarioViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScenarioViewHolder, position: Int) {
        getItem(position)?.let { scenario ->
            holder.bindTo(scenario)
            holder.itemView.setOnClickListener {
                listener.onScenarioClick(scenario.id)
            }
            holder.itemView.setOnLongClickListener {
                listener.onLongScenarioClick(scenario.id, position)
                true
            }
        }
    }

    interface ScenarioClickListener{
        fun onScenarioClick(id : Int)
        fun onLongScenarioClick(id : Int, position: Int)
    }

}