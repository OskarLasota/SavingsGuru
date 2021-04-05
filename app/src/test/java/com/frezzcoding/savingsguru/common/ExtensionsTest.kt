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

    @Test
    fun lowestNumber_list_IsNull(){
        //when
        val nullList : List<Int> = listOf()

        //then
        assert(nullList.lowestNumber() == null)
    }

    @Test
    fun lowestNumber_findsLowestValue(){
        //when
        val list : List<Int> = listOf(-2, 0, -1, 5, 21, 42, 2)

        //then
        assert(list.lowestNumber() == -2)
    }

}