package com.frezzcoding.savingsguru.functionalities.scenarioview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.frezzcoding.savingsguru.R
import com.frezzcoding.savingsguru.common.onNonEmptyTextChangedOnlyNumbers
import com.frezzcoding.savingsguru.data.models.Scenario
import com.frezzcoding.savingsguru.databinding.FragmentScenarioBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScenarioFragment : Fragment() {

    private val viewModel by viewModels<ScenarioViewModel>()
    private val TAG = "ScenarioFragment"
    private lateinit var binding: FragmentScenarioBinding
    private lateinit var scenario: Scenario

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

        val id = arguments?.getInt("id")
            ?: throw IllegalArgumentException("Scenario ID not passed to ScenarioFragment::class")

        viewModel.getScenario(id)
    }

    private fun setListeners() {
        binding.etCalculateGoal.onNonEmptyTextChangedOnlyNumbers {
            if (it.isEmpty()) {
                binding.tvResultYear.text = getString(R.string.n_a)
            } else {
                var months = calculateTimeToGoal(it.toInt())
                binding.tvResultYear.text = mapMonthsIntoYears(months)
            }
        }
    }

    private fun mapMonthsIntoYears(months : Int) : String{
        return if(months < 12){
            "$months ${getString(R.string.months)}"
        }else{
            var years = months / 12
            var remeinder = months % 12
            "${getString(R.string.years_and_months, years.toString(), remeinder.toString())}"
        }
    }

    private fun calculateTimeToGoal(goal: Int): Int {
        val result =
            goal / (if (scenario.income - scenario.expenses == 0) 1 else scenario.income - scenario.expenses)
        return if (result < 0) 0 else result
    }

    private fun setObservers() {
        viewModel.scenario.observe(viewLifecycleOwner, {
            scenario = it
        })
        viewModel.error.observe(viewLifecycleOwner, {

        })
        viewModel.loading.observe(viewLifecycleOwner, {

        })
    }


}