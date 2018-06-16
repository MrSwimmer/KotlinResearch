package com.kotlin_research.kotlinresearch.presentation.result_note

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.kotlin_research.kotlinresearch.App

class ResultNotePresenter : MvpBasePresenter<ResultNoteContract.View>(), ResultNoteContract.Presenter {

    init {
        App.getComponent().inject(this)
    }
}