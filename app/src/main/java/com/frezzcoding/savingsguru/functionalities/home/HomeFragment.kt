package com.frezzcoding.savingsguru.functionalities.home

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.frezzcoding.savingsguru.R
import com.frezzcoding.savingsguru.databinding.FragmentHomeBinding
import com.frezzcoding.savingsguru.functionalities.home.adapter.ScenarioAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.popup_savings_action.*

@AndroidEntryPoint
class HomeFragment : Fragment(), ScenarioAdapter.ScenarioClickListener {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var scenarioAdapter : ScenarioAdapter
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
            binding.homeProgressBar.visibility = if(loading) View.VISIBLE else View.GONE
        })
        viewModel.error.observe(viewLifecycleOwner, { errorMessage ->
            Toast.makeText(this.context, errorMessage, Toast.LENGTH_SHORT).show()
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
        if(id == 0){
            Navigation.findNavController(requireView()).navigate(R.id.addFragment)
        }else{
            findNavController().navigate(
                R.id.action_homeFragment_to_scenarioFragment, bundleOf("id" to id)
            )
        }
    }

    override fun onLongScenarioClick(id: Int, position: Int) {
        var dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.popup_savings_action)
        setActionDialogListeners(id, dialog)
        dialog.show()
    }

    private fun setActionDialogListeners(id : Int, dialog: Dialog){
        dialog.btn_open.setOnClickListener {
            findNavController().navigate(
                R.id.action_homeFragment_to_scenarioFragment, bundleOf("id" to id)
            )
        }
        dialog.btn_remove.setOnClickListener {
            viewModel.removeScenario(id)
            dialog.dismiss()
        }
    }

}