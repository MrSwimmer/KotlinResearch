package com.kotlin_research.kotlinresearch.presentation.notes.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.kotlin_research.kotlinresearch.R

class NoteViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    lateinit var points: TextView

    init {
        points = v.findViewById(R.id.item_note_points)
    }

}