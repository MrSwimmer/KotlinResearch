package com.kotlin_research.kotlinresearch.presentation.result_note

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.hannesdorfmann.mosby3.mvp.conductor.MvpController
import com.kotlin_research.kotlinresearch.R
import com.kotlin_research.kotlinresearch.data.room.Note
import com.kotlin_research.kotlinresearch.domain.format.FormatNote
import java.util.logging.Logger
import kotlin.math.log

class ResultNoteController() : MvpController<ResultNoteContract.View, ResultNoteContract.Presenter>(), ResultNoteContract.View {

    @BindView(R.id.result_pulse_sitting)
    lateinit var pulseSitting: TextView
    @BindView(R.id.result_pulse_standing)
    lateinit var pulseStanding: TextView
    @BindView(R.id.result_zone)
    lateinit var zone: TextView
    @BindView(R.id.result_points)
    lateinit var points: TextView
    @BindView(R.id.result_zone_title)
    lateinit var zoneTitle: TextView
    @BindView(R.id.result_zone_text)
    lateinit var zoneText: TextView
    @BindView(R.id.result_zone_first)
    lateinit var zoneFirst: LinearLayout
    @BindView(R.id.result_zone_second)
    lateinit var zoneSecond: LinearLayout
    @BindView(R.id.result_zone_third)
    lateinit var zoneThird: LinearLayout
    @BindView(R.id.result_zone_fourth)
    lateinit var zoneFourth: LinearLayout
    @BindView(R.id.result_moment)
    lateinit var moment: ImageView

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
        val formatNote = FormatNote.format(note)
        zone.text = formatNote.zone
        points.text = note.points.toString()
        when (note.zone) {
            1 -> {
                zoneFirst.setBackgroundResource(R.color.grey_back)
                zoneTitle.setText(R.string.zone_1t)
                zoneText.setText(R.string.zone_1)
            }
            2 -> {
                zoneSecond.setBackgroundResource(R.color.grey_back)
                zoneTitle.setText(R.string.zone_2t)
                zoneText.setText(R.string.zone_2)
            }
            3 -> {
                zoneThird.setBackgroundResource(R.color.grey_back)
                zoneTitle.setText(R.string.zone_3t)
                zoneText.setText(R.string.zone_3)
            }
            4 -> {
                zoneFourth.setBackgroundResource(R.color.grey_back)
                zoneTitle.setText(R.string.zone_4t)
                zoneText.setText(R.string.zone_4)
            }
        }
        pulseSitting.text = note.pulseSitting
        pulseStanding.text = note.pulseStanding
        moment.setImageResource(formatNote.moment)
        return view
    }
}