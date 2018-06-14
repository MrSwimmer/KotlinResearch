package com.kotlin_research.kotlinresearch.presentation.settings

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.kotlin_research.kotlinresearch.App

class SettingsPresenter : MvpBasePresenter<SettingsContract.View>(), SettingsContract.Presenter {
    init {
        App.getComponent().inject(this)
    }
}