package com.bignerdranch.android.osm.presentation.main.add_note

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
import com.bignerdranch.android.osm.App
import com.bignerdranch.android.osm.R
import com.bignerdranch.android.osm.data.room.Note
import com.bignerdranch.android.osm.presentation.main.result_note.ResultNoteController
import java.util.*

class AddNoteController : MvpController<AddNoteContract.View, AddNoteContract.Presenter>(), AddNoteContract.View {

    @BindView(R.id.add_note_pulse_sitting)
    lateinit var pulseSittingField: EditText
    @BindView(R.id.add_note_pulse_standing)
    lateinit var pulseStandingField: EditText
    @BindView(R.id.add_note_get_result)
    lateinit var getResultButton: ImageView
    @BindView(R.id.add_note_moment_sleep)
    lateinit var sleepImage: ImageView
    @BindView(R.id.add_note_moment_training)
    lateinit var trainImage: ImageView

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

    override fun onAttach(view: View) {
        super.onAttach(view)
        if (afterSleep) {
            sleepImage.setImageResource(R.drawable.ic_alarm_red)
            trainImage.setImageResource(R.drawable.ic_training_grey)
        } else {
            sleepImage.setImageResource(R.drawable.ic_alarm_grey)
            trainImage.setImageResource(R.drawable.ic_training_red)
        }
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
        presenter.addNote(note)
    }

    override fun gotoResult(note: Note) {
        router.replaceTopController(RouterTransaction.with(ResultNoteController(note)))
    }

    @OnClick(R.id.add_note_moment_sleep)
    fun onSleepClick() {
        afterSleep = true
        sleepImage.setImageResource(R.drawable.ic_alarm_red)
        trainImage.setImageResource(R.drawable.ic_training_grey)
    }

    @OnClick(R.id.add_note_moment_training)
    fun onTrainClick() {
        afterSleep = false
        sleepImage.setImageResource(R.drawable.ic_alarm_grey)
        trainImage.setImageResource(R.drawable.ic_training_red)
    }
}