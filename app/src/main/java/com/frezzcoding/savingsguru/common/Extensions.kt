package com.frezzcoding.savingsguru.common

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun List<Int>.highestNumber() : Int?{
    return if(this.isNullOrEmpty()){
        null
    }else {
        var highestValue = this[0]
        this.forEach {value ->
            if(value > highestValue) highestValue = value
        }
        highestValue
    }
}

fun List<Int>.lowestNumber() : Int?{
    return if(this.isNullOrEmpty()){
        null
    }else {
        var lowestValue = this[0]
        this.forEach {value ->
            if(value < lowestValue) lowestValue = value
        }
        lowestValue
    }
}

fun EditText.onTextChanged(onTextChanged : (String) -> Unit){
    this.addTextChangedListener(object : TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            onTextChanged.invoke(s.toString())
        }

        override fun afterTextChanged(s: Editable?) {
        }

    })
}