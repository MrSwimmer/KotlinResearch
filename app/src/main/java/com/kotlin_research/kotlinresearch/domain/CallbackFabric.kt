package com.kotlin_research.kotlinresearch.domain

import com.kotlin_research.kotlinresearch.data.room.Note
import com.kotlin_research.kotlinresearch.domain.interactor.RoomService
import io.reactivex.observers.DisposableSingleObserver

class CallBackFabric {
    companion object {
        fun getNoteCallback(callback: RoomService.NotesCallback): DisposableSingleObserver<List<Note>> {
            return object : DisposableSingleObserver<List<Note>>() {
                override fun onSuccess(notes: List<Note>) {
                    callback.onSuccess(notes)
                }

                override fun onError(e: Throwable) {
                    callback.onError(e)
                }
            }
        }
    }
}