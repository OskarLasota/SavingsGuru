package com.frezzcoding.savingsguru.functionalities.scenarioview

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.frezzcoding.savingsguru.R

class ScenarioFragment : Fragment(R.layout.fragment_scenario) {

    private val viewModel by viewModels<ScenarioViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var id = arguments?.getInt("id")
    }

}