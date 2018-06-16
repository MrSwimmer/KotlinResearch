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
import com.kotlin_research.kotlinresearch.data.room.Note
import com.kotlin_research.kotlinresearch.data.room.NoteDao
import com.kotlin_research.kotlinresearch.presentation.result_note.ResultNoteController
import java.util.*
import javax.inject.Inject

class AddNoteController : MvpController<AddNoteContract.View, AddNoteContract.Presenter>(), AddNoteContract.View {

    @Inject
    lateinit var db: NoteDao

    @BindView(R.id.add_note_pulse_sitting)
    lateinit var pulseSittingField: EditText
    @BindView(R.id.add_note_pulse_standing)
    lateinit var pulseStandingField: EditText
    @BindView(R.id.add_note_get_result)
    lateinit var getResultButton: ImageView

    var afterSleep = false
    lateinit var pulseSitting: String
    lateinit var pulseStanding: String

    override fun createPresenter(): AddNoteContract.Presenter {
        return AddNotePresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view: View = inflater.inflate(R.layout.controller_add_note, container, false)
        ButterKnife.bind(this, view)
        App.getComponent().inject(this)
        return view
    }

    @OnClick(R.id.add_note_get_result)
    fun onGetResultClick() {
        pulseSitting = pulseSittingField.text.toString()
        pulseStanding = pulseStandingField.text.toString()
        presenter.getResult(pulseSitting, pulseStanding)
    }

    override fun setRes(points: Double, zone: Int) {
        val date = Date()
        val note = Note(pulseSitting, pulseStanding, date.time, points, zone, afterSleep)

        db.insert(note)
        router.replaceTopController(RouterTransaction.with(ResultNoteController(note)))
    }
}