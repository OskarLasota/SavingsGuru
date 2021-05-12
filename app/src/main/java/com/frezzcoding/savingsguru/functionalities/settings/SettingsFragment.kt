package com.frezzcoding.savingsguru.functionalities.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.frezzcoding.savingsguru.R
import com.frezzcoding.savingsguru.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private lateinit var binding : FragmentSettingsBinding
    private val viewModel by viewModels<SettingsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_settings , container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        initializeSwitch()
    }

    private fun setListeners(){
        binding.switchNotification.setOnCheckedChangeListener { _, isChecked ->
            //isChecked shows the new checked status
            if(isChecked){
                viewModel.activateNotifications()
            }else{
                viewModel.disableNotifications()
            }
            storeSelection(isChecked)
        }
        binding.btnClearCache.setOnClickListener {
            //todo show popup
            viewModel.clearRoomCache()
        }
    }


    private fun initializeSwitch(){
        //check sharedPreference or datastore
    }

    private fun storeSelection(isChecked : Boolean){
        //store selection in sharedPreferences or datastore
    }

}