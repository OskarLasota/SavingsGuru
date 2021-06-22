package com.frezzcoding.savingsguru.functionalities.newscenario

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.frezzcoding.savingsguru.R
import com.frezzcoding.savingsguru.common.SeekBarOnProgressChanged
import com.frezzcoding.savingsguru.data.models.Scenario
import com.frezzcoding.savingsguru.databinding.FragmentNewscenarioBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewScenarioFragment : Fragment() {

    private lateinit var binding: FragmentNewscenarioBinding
    private val viewModel by viewModels<NewScenarioViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_newscenario, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //should hide bottom nav bar but show back button
        setObservers()
        setListeners()
    }

    private fun setObservers() {
        viewModel.error.observe(viewLifecycleOwner, {
            Toast.makeText(this.context, it, Toast.LENGTH_SHORT).show()
        })
        viewModel.success.observe(viewLifecycleOwner, {
            Toast.makeText(
                requireContext(),
                getString(R.string.entry_added, it.title),
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_addFragment_to_homeFragment)
        })
    }

    private fun setListeners() {
        binding.seekbarRatio.setOnSeekBarChangeListener(object : SeekBarOnProgressChanged {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                adjustRatioValues(progress)
            }
        })

        binding.btnCreate.setOnClickListener {
            if (entryIsValid()) {
                addEntry()
            }
        }
    }

    private fun entryIsValid(): Boolean {
        return true
    }

    private fun adjustRatioValues(progress: Int) {
        binding.tvInvestmentValue.text = (100 - progress).toString()
        binding.tvSavingsValue.text = progress.toString()
    }


    private fun addEntry() {
        if (validateInput()) {
            viewModel.addScenario(
                Scenario(
                    0,
                    binding.etTitle.text.toString(),
                    binding.etMonthlyExpenses.text.toString().toInt(),
                    binding.etMonthlyIncome.text.toString().toInt(),
                    binding.seekbarRatio.progress,
                    100 - binding.seekbarRatio.progress
                )
            )
        }
    }

    private fun validateInput(): Boolean {
        if (binding.etTitle.text.toString().isNullOrEmpty()) {
            viewModel.sendErrorMessage(getString(R.string.error_title_empty))
            return false
        }
        if (binding.etMonthlyExpenses.text.toString().isNullOrEmpty()) {
            viewModel.sendErrorMessage(getString(R.string.error_expenses_empty))
            return false
        }
        if (binding.etMonthlyIncome.text.toString().isNullOrEmpty()) {
            viewModel.sendErrorMessage(getString(R.string.error_income_empty))
            return false
        }
        return true
    }

}