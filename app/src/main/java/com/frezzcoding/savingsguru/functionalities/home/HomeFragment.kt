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
class HomeFragment : Fragment(), ScenarioAdapter.ScenarioClickListener {

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
        setObservers()
        setupAdapter()
        viewModel.getScenarios()
    }

    private fun setObservers(){
        viewModel.loading.observe(viewLifecycleOwner, { loading ->

        })
        viewModel.error.observe(viewLifecycleOwner, { errorMessage ->

        })
        viewModel.scenarios.observe(viewLifecycleOwner, { scenarioList ->
            scenarioAdapter.submitList(scenarioList)
        })
    }

    private fun setupAdapter(){
        scenarioAdapter = ScenarioAdapter(this)
        binding.recyclerScenario.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = scenarioAdapter
        }
    }

    override fun onScenarioClick(id: Int) {
        println("scenario $id clicked")
    }

}