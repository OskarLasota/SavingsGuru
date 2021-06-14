package com.frezzcoding.savingsguru.functionalities.scenarioview

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.frezzcoding.savingsguru.R
import com.frezzcoding.savingsguru.common.onTextChanged
import com.frezzcoding.savingsguru.data.models.Scenario
import com.frezzcoding.savingsguru.databinding.FragmentScenarioBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScenarioFragment : Fragment() {

    private val viewModel by viewModels<ScenarioViewModel>()

    private lateinit var binding : FragmentScenarioBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_scenario, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel
        setListeners()
        setObservers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getInt("id") ?: throw IllegalArgumentException("Scenario ID not passed to ScenarioFragment::class")

        viewModel.getScenario(id)
    }

    private fun setListeners(){

    }


    private fun setObservers(){
        viewModel.scenario.observe(viewLifecycleOwner, {

        })
        viewModel.error.observe(viewLifecycleOwner, {

        })
        viewModel.loading.observe(viewLifecycleOwner, {

        })
    }



}