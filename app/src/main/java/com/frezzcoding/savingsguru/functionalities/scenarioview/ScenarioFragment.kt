package com.frezzcoding.savingsguru.functionalities.scenarioview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.frezzcoding.savingsguru.R
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
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_scenario, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var id = arguments?.getInt("id") ?: 0
        if(id != 0) {
            viewModel.getScenario(id)
        }
        setObservers()
    }

    private fun initializeUI(scenario : Scenario){
        var max = if(scenario.income > scenario.expenses) scenario.income else scenario.expenses
        binding.pbIncome.max = max
        binding.pbExpenses.max = max

        binding.pbIncome.progress = scenario.income
        binding.pbExpenses.progress = scenario.expenses
        binding.tvExpenses.text = getString(R.string.currency) + scenario.expenses.toString()
        binding.tvIncome.text = getString(R.string.currency) + scenario.income.toString()
        binding.tvInvestmentRatio.text = scenario.investmentRatio.toString()
        binding.tvSavingsRatio.text = scenario.savingsRatio.toString()
    }

    private fun setObservers(){
        viewModel.scenario.observe(viewLifecycleOwner, {
            initializeUI(it)
        })
        viewModel.error.observe(viewLifecycleOwner, {

        })
        viewModel.loading.observe(viewLifecycleOwner, {

        })
    }



}