package com.frezzcoding.savingsguru.functionalities.settings

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.frezzcoding.savingsguru.R
import com.frezzcoding.savingsguru.common.SPManager
import com.frezzcoding.savingsguru.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private val viewModel by viewModels<SettingsViewModel>()

    @Inject
    lateinit var spManager: SPManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_settings, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setObservers()
        initialiseUI()
    }

    private fun initialiseUI() {
        binding.switchNotification.isChecked = spManager.getNotificationIsSwitchedOn()
    }

    private fun setObservers() {
        viewModel.clearSuccess.observe(viewLifecycleOwner, {
            if (it) {
                showInformationDialog()
            }
        })
    }

    private fun setListeners() {
        binding.switchNotification.setOnCheckedChangeListener { _, isChecked ->
            //isChecked shows the new checked status
            if (isChecked) {
                viewModel.activateNotifications(requireContext())
            } else {
                viewModel.disableNotifications(requireContext())
            }
            storeSelection(isChecked)
        }
        binding.btnClearCache.setOnClickListener {
            showConfirmDialog()
        }
    }

    private fun showInformationDialog() {
        var dialog = AlertDialog.Builder(context, R.style.AlertDialogTheme)
            .setTitle("")
            .setMessage(R.string.cache_cleared)
            .setPositiveButton(R.string.ok, null)
        dialog.show()
    }

    private fun showConfirmDialog() {
        var dialog = AlertDialog.Builder(context, R.style.AlertDialogTheme)
            .setTitle(R.string.confirm_cache_removal)
            .setMessage(R.string.info_cache_removal)
            .setPositiveButton(R.string.ok) { _, _ ->
                viewModel.clearRoomCache()
            }
            .setNegativeButton(R.string.cancel, null)
        dialog.show()
    }

    private fun storeSelection(isChecked: Boolean) {
        spManager.setNotificationSwitchState(isChecked)
    }

}