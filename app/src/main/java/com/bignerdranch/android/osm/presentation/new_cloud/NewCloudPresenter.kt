package com.bignerdranch.android.osm.presentation.new_cloud

import com.bignerdranch.android.osm.App
import com.bignerdranch.android.osm.domain.interactor.FireService
import com.bignerdranch.android.osm.domain.interactor.RoomService
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import javax.inject.Inject

class NewCloudPresenter : MvpBasePresenter<NewCloudContract.View>(), NewCloudContract.Presenter {
    @Inject
    lateinit var roomService: RoomService
    @Inject
    lateinit var fireService: FireService

    init {
        App.getComponent().inject(this)
    }

    override fun checkCloud() {
        fireService.checkOnCloud()
    }
}