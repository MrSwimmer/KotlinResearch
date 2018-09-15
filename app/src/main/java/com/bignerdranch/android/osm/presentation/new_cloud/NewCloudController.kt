package com.bignerdranch.android.osm.presentation.new_cloud

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.bignerdranch.android.osm.App
import com.bignerdranch.android.osm.R
import com.bignerdranch.android.osm.domain.interactor.FireService
import com.hannesdorfmann.mosby3.mvp.conductor.MvpController
import javax.inject.Inject

class NewCloudController : MvpController<NewCloudContract.View, NewCloudContract.Presenter>(), NewCloudContract.View{

    @BindView(R.id.cloud_radio_group)
    lateinit var radioGroup: RadioGroup

    @Inject
    lateinit var fireService: FireService

    override fun createPresenter(): NewCloudContract.Presenter {
        return NewCloudPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view: View = inflater.inflate(R.layout.controller_cloud, container, false)
        ButterKnife.bind(this, view)
        App.getComponent().inject(this)
        presenter.checkCloud()
        //presenter.setGroup()
        return view
    }

    @OnClick(R.id.cloud_ready)
    fun onReadyClick() {

    }
}