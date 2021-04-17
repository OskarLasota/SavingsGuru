package com.frezzcoding.savingsguru.functionalities.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.frezzcoding.savingsguru.data.models.Scenario
import kotlinx.android.synthetic.main.item_scenario.view.*

class ScenarioViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    fun bindTo(scenario : Scenario){
        itemView.tv_title.text = scenario.title
    }

}
