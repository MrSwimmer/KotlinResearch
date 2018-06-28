package com.bignerdranch.android.osm.presentation.auth.sign_in

import android.content.Intent
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputEditText
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.hannesdorfmann.mosby3.mvp.conductor.MvpController
import com.bignerdranch.android.osm.App
import com.bignerdranch.android.osm.R
import com.bignerdranch.android.osm.domain.interactor.FireService
import com.bluelinelabs.conductor.RouterTransaction
import javax.inject.Inject

class SignInController : MvpController<CloudContract.View, CloudContract.Presenter>(), CloudContract.View {

    @BindView(R.id.sign_in_email)
    lateinit var email: TextInputEditText
    @BindView(R.id.sign_in_pass)
    lateinit var pass: TextInputEditText
    @BindView(R.id.sign_in_skip)
    lateinit var skip: TextView

    @Inject
    lateinit var fireService: FireService

    override fun createPresenter(): CloudContract.Presenter {
        return CloudPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view: View = inflater.inflate(R.layout.controller_sign_in, container, false)
        ButterKnife.bind(this, view)
        App.getComponent().inject(this)
        skip.text = android.text.Html.fromHtml("<u>Пропустить</u>")
        return view
    }

    @OnClick(R.id.sign_in_enter)
    fun onEnterClick() {
        fireService.signIn(email.text.toString(), pass.text.toString(), object : FireService.AuthCallBack {
            override fun onSuccess(success: Boolean) {
                router.replaceTopController(RouterTransaction.with(CloudController()))
                Snackbar.make(view!!, "Вход выполнен успешно", Snackbar.LENGTH_SHORT).show()
            }

            override fun onError(e: Throwable) {
                Snackbar.make(view!!, e.message!!, Snackbar.LENGTH_SHORT).show()
            }

        })
    }

    @OnClick(R.id.sign_in_reg)
    fun onRegClick() {
        fireService.signUp(email.text.toString(), pass.text.toString(), object : FireService.AuthCallBack {
            override fun onSuccess(success: Boolean) {

            }

            override fun onError(e: Throwable) {
            }

        })
    }

    @OnClick(R.id.sign_in_skip)
    fun onSkipClick() {
        activity!!.finish()
    }
}