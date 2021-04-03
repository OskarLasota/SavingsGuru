package com.frezzcoding.savingsguru.common

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