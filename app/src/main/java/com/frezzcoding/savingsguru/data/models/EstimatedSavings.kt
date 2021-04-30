package com.frezzcoding.savingsguru.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_savings")
data class EstimatedSavings(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var amount: Int = 0,
    var year: Int = 0,
    var lastEntry: Boolean = true
)