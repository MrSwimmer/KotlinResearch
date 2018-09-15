package com.bignerdranch.android.osm.presentation.result_note

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.bignerdranch.android.osm.App

class ResultNotePresenter : MvpBasePresenter<ResultNoteContract.View>(), ResultNoteContract.Presenter {

    init {
        App.getComponent().inject(this)
    }
}