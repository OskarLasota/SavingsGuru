package com.frezzcoding.savingsguru.common

import org.junit.Test

class ExtensionsTest {

    @Test
    fun highestNumber_listIsNull(){
        //when
        val nullList : List<Int> = listOf()

        //then
        assert(nullList.highestNumber() == null)
    }

    @Test
    fun highestNumber_findsHighestValue(){
        //when
        val list : List<Int> = listOf(-2, -1, 9 , 0, 4, 3, 42, 1)

        //then
        assert(list.highestNumber() == 42)
    }

}