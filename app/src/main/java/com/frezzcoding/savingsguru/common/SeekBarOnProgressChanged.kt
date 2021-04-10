package com.frezzcoding.savingsguru.common

import android.widget.SeekBar


//enforcing interface segregation
interface SeekBarOnProgressChanged : SeekBar.OnSeekBarChangeListener{
    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {

    }
}