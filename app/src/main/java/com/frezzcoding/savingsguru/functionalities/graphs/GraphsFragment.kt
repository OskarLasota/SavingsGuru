package com.frezzcoding.savingsguru.functionalities.graphs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.frezzcoding.savingsguru.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_graph.*

@AndroidEntryPoint
class GraphsFragment : Fragment(R.layout.fragment_graph) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("set data points")
        line_graph.setDataPoints(listOf(5,15,3,6,1,2,5))
    }

}