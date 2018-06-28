package com.bignerdranch.android.osm.presentation.auth.sign_in

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.OnClick
import com.hannesdorfmann.mosby3.mvp.conductor.MvpController
import com.bignerdranch.android.osm.App
import com.bignerdranch.android.osm.R

class CloudController : MvpController<CloudContract.View, CloudContract.Presenter>(), CloudContract.View {

    override fun createPresenter(): CloudContract.Presenter {
        return CloudPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view: View = inflater.inflate(R.layout.controller_cloud, container, false)
        ButterKnife.bind(this, view)
        App.getComponent().inject(this)
        return view
    }

    @OnClick(R.id.sign_in_enter)
    fun onEnterClick() {

    }
}