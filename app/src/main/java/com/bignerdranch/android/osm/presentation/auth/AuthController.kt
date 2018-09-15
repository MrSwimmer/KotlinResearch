package com.bignerdranch.android.osm.presentation.auth

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bignerdranch.android.osm.R
import com.hannesdorfmann.mosby3.mvp.conductor.MvpController
import com.vk.sdk.VKScope
import com.vk.sdk.VKSdk
import kotlinx.android.synthetic.main.controller_auth.view.*

class AuthController: MvpController<AuthContract.View, AuthContract.Presenter>(), AuthContract.View {
    override fun createPresenter(): AuthContract.Presenter {
        return AuthPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val v = inflater.inflate(R.layout.controller_auth, container, false)
        v.vk.setOnClickListener({
            val scope = arrayOf(VKScope.PAGES)
            VKSdk.login(activity!!, scope[0])
        })
        return v
    }
}