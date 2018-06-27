package com.kotlin_research.kotlinresearch.presentation.auth.sign_in

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.kotlin_research.kotlinresearch.App
import com.kotlin_research.kotlinresearch.domain.interactor.RoomService
import javax.inject.Inject

class SignInPresenter : MvpBasePresenter<CloudContract.View>(), CloudContract.Presenter {

    @Inject
    lateinit var roomService: RoomService

    init {
        App.getComponent().inject(this)
    }

}