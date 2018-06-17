package com.kotlin_research.kotlinresearch.presentation.result_note

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import butterknife.ButterKnife
import com.hannesdorfmann.mosby3.mvp.conductor.MvpController
import com.kotlin_research.kotlinresearch.R
import com.kotlin_research.kotlinresearch.data.room.Note
import java.util.logging.Logger
import kotlin.math.log

class ResultNoteController() : MvpController<ResultNoteContract.View, ResultNoteContract.Presenter>(), ResultNoteContract.View {

    /*@BindView(R.id.add_note_pulse_sitting)
    lateinit var pulseSittingField: EditText
    @BindView(R.id.add_note_pulse_standing)
    lateinit var pulseStandingField: EditText
    @BindView(R.id.add_note_get_result)
    lateinit var getResultButton: ImageView*/

    var afterSleep = false
    private lateinit var note: Note

    constructor(note: Note) : this() {
        this.note = note
    }

    override fun createPresenter(): ResultNoteContract.Presenter {
        return ResultNotePresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view: View = inflater.inflate(R.layout.controller_result_note, container, false)
        ButterKnife.bind(this, view)
        Log.i("code", "points " + note.points.toString())
        //App.getComponent().inject(this)
        return view
    }

    /*@OnClick(R.id.add_note_get_result)
    fun onGetResultClick() {

    }*/
}