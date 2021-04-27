package com.frezzcoding.savingsguru.functionalities.graphs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.frezzcoding.savingsguru.R
import com.frezzcoding.savingsguru.data.models.EstimatedSavings
import com.frezzcoding.savingsguru.databinding.FragmentGraphBinding
import com.frezzcoding.savingsguru.functionalities.graphs.adapter.GraphsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_graph.*

@AndroidEntryPoint
class GraphsFragment : Fragment() {

    private lateinit var binding : FragmentGraphBinding
    private lateinit var graphsAdapter : GraphsAdapter
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
        setupAdapter()
        //todo idea to allow user to add entries that represent years or months so that they can estimate their future savings
    }

    private fun setupAdapter(){
        graphsAdapter = GraphsAdapter()
        binding.recyclerSavings.apply {
            layoutManager = GridLayoutManager(requireContext(), 1)
            adapter = graphsAdapter
        }
        graphsAdapter.submitList(listOf(EstimatedSavings(1, 1, 1)))
    }

    private fun setListeners(){

    }

    private fun updateGraph(){
        var dataPoints = listOf(2,41,23,34,19,24,11,6)

        line_graph.setDataPoints(dataPoints)

    }

}