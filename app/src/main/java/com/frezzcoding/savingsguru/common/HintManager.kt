package com.frezzcoding.savingsguru.common

import android.content.Context
import android.widget.Toast
import com.frezzcoding.savingsguru.R

class HintManager(private val context: Context) {

    fun showSwipeToRemoveHint() =
        Toast.makeText(context, context.getString(R.string.hint_swipe_to_remove), Toast.LENGTH_LONG)
            .show()

}