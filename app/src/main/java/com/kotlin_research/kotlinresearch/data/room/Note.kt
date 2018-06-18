package com.kotlin_research.kotlinresearch.data.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "notes")
data class Note(var pulseSitting: String, var pulseStanding: String, var date: Long, var points: Double, var zone: Int, var afterSleep: Boolean) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}