package com.frezzcoding.savingsguru.functionalities.newscenario

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.frezzcoding.savingsguru.R
import com.frezzcoding.savingsguru.common.SeekBarOnProgressChanged
import com.frezzcoding.savingsguru.databinding.FragmentNewscenarioBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewScenarioFragment : Fragment() {

    private lateinit var binding : FragmentNewscenarioBinding

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
        }

    }

    private fun adjustRatioValues(progress : Int){
        binding.tvInvestmentValue
        binding.tvSavingsValue
    }


    private fun addEntry(){
        binding.etTitle.text
        binding.etMonthlyExpenses.text
        binding.etMonthlyIncome.text
    }

}