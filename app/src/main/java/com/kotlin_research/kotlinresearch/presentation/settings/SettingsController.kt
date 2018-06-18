package com.kotlin_research.kotlinresearch.presentation.settings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.hannesdorfmann.mosby3.mvp.conductor.MvpController
import com.kotlin_research.kotlinresearch.App
import com.kotlin_research.kotlinresearch.R

class SettingsController : MvpController<SettingsContract.View, SettingsContract.Presenter>(), SettingsContract.View {

    override fun createPresenter(): SettingsContract.Presenter {
        return SettingsPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view: View = inflater.inflate(R.layout.controller_settings, container, false)
        ButterKnife.bind(this, view)
        App.getComponent().inject(this)
        return view
    }
}