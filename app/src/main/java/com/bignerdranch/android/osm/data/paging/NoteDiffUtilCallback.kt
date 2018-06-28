package com.bignerdranch.android.osm.data.paging

import android.support.v7.util.DiffUtil
import com.bignerdranch.android.osm.data.room.Note

class NoteDiffUtilCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.points == newItem.points && oldItem.afterSleep == newItem.afterSleep
    }
}