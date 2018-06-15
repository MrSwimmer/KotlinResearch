package com.kotlin_research.kotlinresearch.presentation.single_note

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.hannesdorfmann.mosby3.mvp.conductor.MvpController
import com.kotlin_research.kotlinresearch.App
import com.kotlin_research.kotlinresearch.R
import com.kotlin_research.kotlinresearch.presentation.settings.NotesContract
import com.kotlin_research.kotlinresearch.presentation.settings.NotesPresenter

class SingleNoteController : MvpController<SingleNoteContract.View, SingleNoteContract.Presenter>(), NotesContract.View {

    override fun createPresenter(): SingleNoteContract.Presenter {
        return SingleNotePresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        var view: View = inflater.inflate(R.layout.controller_single_note, container, false)
        ButterKnife.bind(this, view)
        App.getComponent().inject(this)
        return view
    }
}