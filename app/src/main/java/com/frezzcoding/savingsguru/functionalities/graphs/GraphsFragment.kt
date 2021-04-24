package com.frezzcoding.savingsguru.functionalities.graphs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.frezzcoding.savingsguru.R
import com.frezzcoding.savingsguru.databinding.FragmentGraphBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_graph.*

@AndroidEntryPoint
class GraphsFragment : Fragment() {

    private lateinit var binding : FragmentGraphBinding
    private var initial = 0
    private var deposits = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_graph, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners(){
        binding.etInitialSavings.doOnTextChanged { text, _, _, _ ->
            if(!text.isNullOrEmpty()) {
                initial = text.toString().toInt()
            }
        }
        binding.etRegularDeposits.doOnTextChanged { text, _, _, _ ->
            if(!text.isNullOrEmpty()) {
                deposits = text.toString().toInt()
            }
        }
        binding.btnCalcualte.setOnClickListener {
            updateGraph()
        }
    }

    private fun updateGraph(){
        var amount = initial
        var dataPoints = arrayListOf<Int>()
        for(i in 1..25) {
            dataPoints.add(amount)
            for (i in 1..12) {
                if(interest <= 0){
                    amount += deposits
                } else if(amount <= 0) {
                    if(deposits <= 0){
                        //needs deposit if initial is 0
                    } else {
                        amount += deposits
                        amount += (amount * (interest / 100))
                    }
                }else {
                    amount += (amount * (interest / 100)) + deposits
                }
            }
        }
        line_graph.setDataPoints(dataPoints)

    }

}