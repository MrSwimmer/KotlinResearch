package com.bignerdranch.android.osm.presentation.add_note

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.OnClick
import com.bluelinelabs.conductor.RouterTransaction
import com.hannesdorfmann.mosby3.mvp.conductor.MvpController
import com.bignerdranch.android.osm.App
import com.bignerdranch.android.osm.R
import com.bignerdranch.android.osm.data.room.Note
import com.bignerdranch.android.osm.presentation.result_note.ResultNoteController
import com.bignerdranch.android.osm.presentation.watch.list.ListController
import kotlinx.android.synthetic.main.controller_add_note.view.*
import java.util.*

class AddNoteController : MvpController<AddNoteContract.View, AddNoteContract.Presenter>(), AddNoteContract.View {

    /*@BindView(R.id.add_note_pulse_sitting)
    lateinit var pulseSittingField: EditText
    @BindView(R.id.add_note_pulse_standing)
    lateinit var pulseStandingField: EditText
    @BindView(R.id.add_note_get_result)
    lateinit var getResultButton: ImageView
    @BindView(R.id.add_note_moment_sleep)
    lateinit var sleepImage: ImageView
    @BindView(R.id.add_note_moment_training)
    lateinit var trainImage: ImageView*/

    var afterSleep = false
    lateinit var pulseSitting: String
    lateinit var pulseStanding: String

    override fun createPresenter(): AddNoteContract.Presenter {
        return AddNotePresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view: View = inflater.inflate(R.layout.controller_add_note, container, false)
        //ButterKnife.bind(this, view)
        App.getComponent().inject(this)
        view.addNote.setOnClickListener({
            onGetResultClick()
        })
        view.sleepImage.setOnClickListener({
            onSleepClick()
        })
        view.trainImage.setOnClickListener({
            onTrainClick()
        })
        view.gotoWatch.setOnClickListener({
            onWatchClick()
        })
        return view
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        if (afterSleep) {
            view.sleepImage.setImageResource(R.drawable.ic_alarm_red)
            view.trainImage.setImageResource(R.drawable.ic_training_grey)
        } else {
            view.sleepImage.setImageResource(R.drawable.ic_alarm_grey)
            view.trainImage.setImageResource(R.drawable.ic_training_red)
        }
    }

    //@OnClick(R.id.add_note_get_result)
    fun onGetResultClick() {
        pulseSitting = view!!.pulseSittingField.text.toString()
        pulseStanding = view!!.pulseStandingField.text.toString()
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

    //@OnClick(R.id.gotoWatch)
    fun onWatchClick() {
        router.pushController(RouterTransaction.with(ListController()))
    }

    //@OnClick(R.id.sleepImage)
    fun onSleepClick() {
        afterSleep = true
        view!!.sleepImage.setImageResource(R.drawable.ic_alarm_red)
        view!!.trainImage.setImageResource(R.drawable.ic_training_grey)
    }

    //@OnClick(R.id.trainImage)
    fun onTrainClick() {
        afterSleep = false
        view!!.sleepImage.setImageResource(R.drawable.ic_alarm_grey)
        view!!.trainImage.setImageResource(R.drawable.ic_training_red)
    }
}