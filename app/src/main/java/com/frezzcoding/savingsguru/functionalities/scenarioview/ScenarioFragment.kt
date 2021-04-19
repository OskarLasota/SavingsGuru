package com.frezzcoding.savingsguru.functionalities.scenarioview

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.frezzcoding.savingsguru.R

class ScenarioFragment : Fragment(R.layout.fragment_scenario) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var id = arguments?.getInt("id")
    }

}