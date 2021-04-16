package com.frezzcoding.savingsguru.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_scenarios")
data class Scenario (
        @PrimaryKey(autoGenerate = true)
        val id : Int = 0,
        val title : String,
        val expenses : Int,
        val income : Int,
        val savingsRatio : Int,
        val investmentRatio : Int
)