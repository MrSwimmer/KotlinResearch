package com.kotlin_research.kotlinresearch.presentation.add_note

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.bluelinelabs.conductor.RouterTransaction
import com.hannesdorfmann.mosby3.mvp.conductor.MvpController
import com.kotlin_research.kotlinresearch.App
import com.kotlin_research.kotlinresearch.R
import com.kotlin_research.kotlinresearch.presentation.result_note.ResultNoteContract
import com.kotlin_research.kotlinresearch.presentation.result_note.ResultNoteController

class AddNoteController : MvpController<AddNoteContract.View, AddNoteContract.Presenter>(), AddNoteContract.View {

    @BindView(R.id.add_note_pulse_sitting)
    lateinit var pulseSittingField: EditText
    @BindView(R.id.add_note_pulse_standing)
    lateinit var pulseStandingField: EditText
    @BindView(R.id.add_note_get_result)
    lateinit var getResultButton: ImageView

    var afterSleep = false

    override fun createPresenter(): AddNoteContract.Presenter {
        return AddNotePresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        var view: View = inflater.inflate(R.layout.controller_add_note, container, false)
        ButterKnife.bind(this, view)
        App.getComponent().inject(this)
        return view
    }

    @OnClick(R.id.add_note_get_result)
    fun onGetResultClick() {
        var pulseSitting = pulseSittingField.text.toString()
        var pulseStanding = pulseStandingField.text.toString()
        presenter.getResult(pulseSitting, pulseStanding)
    }

    override fun setRes(points: Double, zone: Int) {
        router.replaceTopController(RouterTransaction.with(ResultNoteController()))
    }
}