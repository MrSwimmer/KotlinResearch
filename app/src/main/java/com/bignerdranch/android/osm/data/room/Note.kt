package com.bignerdranch.android.osm.data.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(var pulseSitting: String, var pulseStanding: String, var date: Long, var points: Double, var zone: Int, var afterSleep: Boolean) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}