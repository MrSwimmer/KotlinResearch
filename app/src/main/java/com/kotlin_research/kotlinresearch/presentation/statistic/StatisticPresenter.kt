package com.kotlin_research.kotlinresearch.presentation.statistic

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.kotlin_research.kotlinresearch.App
import com.kotlin_research.kotlinresearch.presentation.settings.NotesContract

class StatisticPresenter : MvpBasePresenter<NotesContract.View>(), NotesContract.Presenter {
    init {
        App.getComponent().inject(this)
    }
}