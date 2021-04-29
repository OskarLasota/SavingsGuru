package com.frezzcoding.savingsguru.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_savings")
data class EstimatedSavings(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var amount: Int,
    var year: Int,
    var lastEntry: Boolean
)