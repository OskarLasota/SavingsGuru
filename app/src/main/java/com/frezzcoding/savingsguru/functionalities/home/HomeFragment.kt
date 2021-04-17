package com.frezzcoding.savingsguru.functionalities.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.frezzcoding.savingsguru.R
import com.frezzcoding.savingsguru.databinding.FragmentHomeBinding
import com.frezzcoding.savingsguru.functionalities.home.adapter.ScenarioAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var scenarioAdapter : ScenarioAdapter
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        viewModel.getScenarios()
        //scenarioAdapter.submitList()
    }

    private fun setupAdapter(){
        scenarioAdapter = ScenarioAdapter().apply {
            //set listener
        }
        binding.recyclerScenario.apply {
            GridLayoutManager(requireContext(), 2)
            adapter = scenarioAdapter
        }
    }

}