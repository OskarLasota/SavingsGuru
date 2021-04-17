package com.frezzcoding.savingsguru.functionalities.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.frezzcoding.savingsguru.R
import com.frezzcoding.savingsguru.data.models.Scenario

class ScenarioAdapter : ListAdapter<Scenario, ScenarioViewHolder>(ScenarioDiffUtil()) {


    class ScenarioDiffUtil : DiffUtil.ItemCallback<Scenario>() {
        override fun areItemsTheSame(oldItem: Scenario, newItem: Scenario): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Scenario, newItem: Scenario): Boolean {
            return oldItem.id == newItem.id //check content too
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScenarioViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_scenario, parent, false)
        return ScenarioViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScenarioViewHolder, position: Int) {
        getItem(position)?.let { scenario ->
            holder.bindTo(scenario)
        }
    }

}