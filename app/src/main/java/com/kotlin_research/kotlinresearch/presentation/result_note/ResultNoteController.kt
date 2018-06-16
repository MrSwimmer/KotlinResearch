package com.kotlin_research.kotlinresearch.presentation.result_note

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

class ResultNoteController : MvpController<ResultNoteContract.View, ResultNoteContract.Presenter>(), ResultNoteContract.View {

    /*@BindView(R.id.add_note_pulse_sitting)
    lateinit var pulseSittingField: EditText
    @BindView(R.id.add_note_pulse_standing)
    lateinit var pulseStandingField: EditText
    @BindView(R.id.add_note_get_result)
    lateinit var getResultButton: ImageView*/

    var afterSleep = false

    override fun createPresenter(): ResultNoteContract.Presenter {
        return ResultNotePresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        var view: View = inflater.inflate(R.layout.controller_result_note, container, false)
        ButterKnife.bind(this, view)
        //App.getComponent().inject(this)
        return view
    }

    /*@OnClick(R.id.add_note_get_result)
    fun onGetResultClick() {

    }*/
}