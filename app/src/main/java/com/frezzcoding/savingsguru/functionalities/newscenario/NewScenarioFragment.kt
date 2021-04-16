package com.frezzcoding.savingsguru.functionalities.newscenario

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.frezzcoding.savingsguru.R
import com.frezzcoding.savingsguru.common.SeekBarOnProgressChanged
import com.frezzcoding.savingsguru.data.models.Scenario
import com.frezzcoding.savingsguru.databinding.FragmentNewscenarioBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewScenarioFragment : Fragment() {

    private lateinit var binding : FragmentNewscenarioBinding
    private val viewModel by viewModels<NewScenarioViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_newscenario , container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //should hide bottom nav bar but show back button

        setListeners()
    }

    private fun setListeners(){
        binding.seekbarRatio.setOnSeekBarChangeListener(object : SeekBarOnProgressChanged {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                adjustRatioValues(progress)
            }
        })

        binding.btnCreate.setOnClickListener {
            addEntry()
            viewModel.addScenario(Scenario(1))
        }

    }

    private fun adjustRatioValues(progress : Int){
        binding.tvInvestmentValue.text = (100 - progress).toString()
        binding.tvSavingsValue.text = progress.toString()
    }


    private fun addEntry(){
        binding.etTitle.text
        binding.etMonthlyExpenses.text
        binding.etMonthlyIncome.text
    }

}