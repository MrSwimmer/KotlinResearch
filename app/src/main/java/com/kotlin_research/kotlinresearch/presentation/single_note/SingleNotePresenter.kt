package com.kotlin_research.kotlinresearch.presentation.single_note

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.kotlin_research.kotlinresearch.App
import com.kotlin_research.kotlinresearch.presentation.settings.NotesContract

class SingleNotePresenter : MvpBasePresenter<SingleNoteContract.View>(), SingleNoteContract.Presenter {
    init {
        App.getComponent().inject(this)
    }
}