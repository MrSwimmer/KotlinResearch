package com.kotlin_research.kotlinresearch.presentation.settings

import android.annotation.SuppressLint
import android.util.Log
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.kotlin_research.kotlinresearch.App
import com.kotlin_research.kotlinresearch.data.room.Note
import com.kotlin_research.kotlinresearch.data.room.NoteDao
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import javax.inject.Inject

class NotesPresenter : MvpBasePresenter<NotesContract.View>(), NotesContract.Presenter {

    @Inject
    lateinit var db: NoteDao

    init {
        App.getComponent().inject(this)
    }

    @SuppressLint("CheckResult")
    override fun setRecyclerData() {
        db.getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { notes ->
                    run {
                        Log.i("code", "accept " + notes.size)
                        view.initAdapter(notes)
                    }
                }
    }
}