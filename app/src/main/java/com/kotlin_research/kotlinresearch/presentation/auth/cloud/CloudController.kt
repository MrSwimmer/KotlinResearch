package com.kotlin_research.kotlinresearch.presentation.auth.sign_in

import android.support.design.widget.TextInputEditText
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.hannesdorfmann.mosby3.mvp.conductor.MvpController
import com.kotlin_research.kotlinresearch.App
import com.kotlin_research.kotlinresearch.R

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