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
class GraphsFragment : Fragment(), GraphsAdapter.OnClickListenerSavings{

    private lateinit var binding : FragmentGraphBinding
    private lateinit var graphsAdapter : GraphsAdapter

    private var list = arrayListOf(EstimatedSavings(1, 1, 1, lastEntry = false), EstimatedSavings(2,2,2,true))

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_graph, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
    }

    private fun setupAdapter(){
        graphsAdapter = GraphsAdapter(this)
        binding.recyclerSavings.apply {
            layoutManager = GridLayoutManager(requireContext(), 1)
            adapter = graphsAdapter
        }
        graphsAdapter.submitList(list)
    }


    override fun addAnotherClick(id : Int, position : Int) {
        list.forEach{
            if(it.id == id){
                it.lastEntry = false
                graphsAdapter.notifyItemChanged(position)
                return@forEach
            }
        }
        list.add(EstimatedSavings(4,0,0,true))
    }

}