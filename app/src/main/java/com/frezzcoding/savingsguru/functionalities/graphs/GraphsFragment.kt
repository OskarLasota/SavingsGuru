package com.frezzcoding.savingsguru.functionalities.graphs

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.frezzcoding.savingsguru.R
import com.frezzcoding.savingsguru.common.HintManager
import com.frezzcoding.savingsguru.data.models.EstimatedSavings
import com.frezzcoding.savingsguru.databinding.FragmentGraphBinding
import com.frezzcoding.savingsguru.functionalities.graphs.adapter.GraphsAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class GraphsFragment : Fragment(), GraphsAdapter.OnClickListenerSavings {

    private lateinit var binding: FragmentGraphBinding
    private lateinit var graphsAdapter: GraphsAdapter
    private val viewModel by viewModels<GraphsViewModel>()
    @Inject
    lateinit var hintManager: HintManager

    private var popupShown: Boolean = false
    private lateinit var list: List<EstimatedSavings>

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
        setupObservers()
        setupListeners()
        viewModel.getInitialSavings()
        //hintManager.showSwipeToRemoveHint()
    }

    private fun setupListeners() {
        var touchHelper = ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun getSwipeDirs(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                if (list[viewHolder.adapterPosition].lastEntry) return 0
                return super.getSwipeDirs(recyclerView, viewHolder)
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                removeSavingsInstance(viewHolder.adapterPosition)
                (list as ArrayList<EstimatedSavings>).removeAt(viewHolder.adapterPosition)
                graphsAdapter.notifyItemRemoved(viewHolder.adapterPosition)
                setupGraph(list.map { it.amount })
            }
        })
        touchHelper.attachToRecyclerView(binding.recyclerSavings)
    }

    private fun removeSavingsInstance(position: Int) {
        showDialog()
        viewModel.removeEstimatedSavings(list[position])
    }

    private fun showDialog() {
        if (!popupShown) {
            var dialog = AlertDialog.Builder(context, R.style.AlertDialogTheme)
                .setTitle("")
                .setMessage(R.string.entry_removed)
                .setPositiveButton(R.string.ok, null)
            dialog.show()
            popupShown = true
        }
    }

    private fun setupObservers() {
        viewModel.initialSavings.observe(viewLifecycleOwner, { savingsList ->
            list = savingsList
            (list as ArrayList).add(EstimatedSavings(0, 0, 0, true))
            setupGraph(list.map { it.amount })
            graphsAdapter.submitList(list)
        })
        viewModel.updatedSavings.observe(viewLifecycleOwner, { updatedList ->
            list = updatedList
            (list as ArrayList).add(EstimatedSavings(0, 0, 0, true))
            setupGraph(list.map { it.amount })
            graphsAdapter.submitList(list)
            graphsAdapter.notifyItemChanged(list.size - 2)
        })
        viewModel.error.observe(viewLifecycleOwner, {

        })
        viewModel.loading.observe(viewLifecycleOwner, {

        })
    }

    private fun setupGraph(list: List<Int>) {
        (list as ArrayList).removeLast()
        if (list.size > 1) {
            binding.lineGraph.setDataPoints(list)
            binding.tvNoGraphMessage.visibility = View.GONE
        } else {
            binding.lineGraph.setDataPoints(list)
            binding.tvNoGraphMessage.visibility = View.VISIBLE
        }
    }

    private fun setupAdapter() {
        graphsAdapter = GraphsAdapter(this)
        binding.recyclerSavings.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = graphsAdapter
        }
    }

    override fun confirmSavings(amount: Int, position: Int) {
        viewModel.addSavings(amount)
        graphsAdapter.notifyItemChanged(position)
    }

    override fun notifyError(message: String) {
        Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
    }

    override fun updateSavings(estimatedSavings: EstimatedSavings) {
        viewModel.updateEstimatedSavingsAmount(estimatedSavings)
    }

}