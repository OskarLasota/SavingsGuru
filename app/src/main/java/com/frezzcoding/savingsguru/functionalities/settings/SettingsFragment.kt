package com.frezzcoding.savingsguru.functionalities.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.frezzcoding.savingsguru.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //should hide bottom nav bar but show back button
    }
}