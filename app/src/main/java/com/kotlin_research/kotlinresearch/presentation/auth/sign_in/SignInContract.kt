package com.kotlin_research.kotlinresearch.presentation.auth.sign_in

import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

interface SignInContract {
    interface View : MvpView
    interface Presenter : MvpPresenter<View>
}