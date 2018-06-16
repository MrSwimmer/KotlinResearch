package com.kotlin_research.kotlinresearch.data.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "notes")
class Note(pulseSitting: String, pulseStanding: String, date: Date, zone: Int, afterSleep: Boolean) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    /*lateinit var pulseSitting: String
    lateinit var pulseStanding: String
    lateinit var date: Date
    var zone = 0
    var afterSleep = false*/
}