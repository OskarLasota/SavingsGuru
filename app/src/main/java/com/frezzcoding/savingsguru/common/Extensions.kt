package com.frezzcoding.savingsguru.common

fun List<Int>.highestNumber() : Int{
    var highestValue = this[0]
    this.forEach {value ->
        if(value > highestValue) highestValue = value
    }
    return highestValue
}