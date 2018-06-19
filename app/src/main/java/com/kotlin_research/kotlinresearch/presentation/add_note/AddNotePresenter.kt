package com.kotlin_research.kotlinresearch.presentation.add_note

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.kotlin_research.kotlinresearch.App
import com.kotlin_research.kotlinresearch.data.room.Note
import com.kotlin_research.kotlinresearch.data.room.NoteDao
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class AddNotePresenter : MvpBasePresenter<AddNoteContract.View>(), AddNoteContract.Presenter {
    @Inject
    lateinit var db: NoteDao

    init {
        App.getComponent().inject(this)
    }

    override fun getResult(pulseSitting: String, pulseStanding: String) {
        val x = 2.27
        val y = 0.5
        val points = 14.5 - y * (pulseSitting.toFloat() - 40) / 3.5 - (pulseStanding.toFloat() - pulseSitting.toFloat()) / x * 0.5
        val zone = getZone(points)
        view.setRes(points, zone)
    }

    fun getZone(points: Double): Int {
        var n = 0
        if (points >= 7.5)
            n = 1
        if (points >= 5 && points < 7.5)
            n = 2
        if (points >= 2.5 && points < 5)
            n = 3
        if (points < 2.5)
            n = 4
        return n
    }

    override fun addNote(note: Note) {
        Observable.create(Observable.OnSubscribe<String> { subscriber ->
            db.insert(note)
            subscriber.onCompleted()
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<String>() {
                    override fun onNext(t: String?) {
                    }

                    override fun onCompleted() {
                        view.gotoResult(note)
                    }

                    override fun onError(e: Throwable) {
                    }

                })
    }
}