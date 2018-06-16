package com.kotlin_research.kotlinresearch.presentation.settings

import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.bluelinelabs.conductor.RouterTransaction
import com.hannesdorfmann.mosby3.mvp.conductor.MvpController
import com.kotlin_research.kotlinresearch.App
import com.kotlin_research.kotlinresearch.R
import com.kotlin_research.kotlinresearch.presentation.add_note.AddNoteController

class NotesController : MvpController<NotesContract.View, NotesContract.Presenter>(), NotesContract.View {

    @BindView(R.id.notes_recycler)
    lateinit var recyclerView: RecyclerView
    @BindView(R.id.notes_fab)
    lateinit var floatingActionButton: FloatingActionButton

    override fun createPresenter(): NotesContract.Presenter {
        return NotesPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        var view: View = inflater.inflate(R.layout.controller_notes, container, false)
        ButterKnife.bind(this, view)
        App.getComponent().inject(this)
        return view
    }

    @OnClick(R.id.notes_fab)
    fun onFABClick() {
        router.pushController(RouterTransaction.with(AddNoteController()))
    }
}