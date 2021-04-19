package com.frezzcoding.savingsguru.functionalities.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.frezzcoding.savingsguru.data.models.Scenario
import com.frezzcoding.savingsguru.databinding.ItemScenarioBinding
import kotlinx.android.synthetic.main.item_scenario.view.*

class ScenarioViewHolder(var binding : ItemScenarioBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(scenario : Scenario){
        binding.scenario = scenario
    }

}
