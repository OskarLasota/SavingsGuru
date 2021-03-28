package com.frezzcoding.savingsguru.functionalities.graphs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.frezzcoding.savingsguru.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_graph.*

@AndroidEntryPoint
class GraphsFragment : Fragment(R.layout.fragment_graph) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        line_graph.setDataPoints(listOf(15,3,6,1,2,5,21,6,2,1,5,1,3))
    }

}