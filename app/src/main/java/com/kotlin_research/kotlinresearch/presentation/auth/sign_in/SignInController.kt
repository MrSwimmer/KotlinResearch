package com.kotlin_research.kotlinresearch.presentation.auth.sign_in

import android.support.design.widget.TextInputEditText
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.bluelinelabs.conductor.RouterTransaction
import com.hannesdorfmann.mosby3.mvp.conductor.MvpController
import com.kotlin_research.kotlinresearch.App
import com.kotlin_research.kotlinresearch.R

class SignInController : MvpController<CloudContract.View, CloudContract.Presenter>(), CloudContract.View {

    @BindView(R.id.sign_in_email)
    lateinit var email: TextInputEditText
    @BindView(R.id.sign_in_pass)
    lateinit var pass: TextInputEditText
    @BindView(R.id.sign_in_skip)
    lateinit var skip: TextView


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

    }

    @OnClick(R.id.sign_in_reg)
    fun onRegClick() {

    }

    @OnClick(R.id.sign_in_vk)
    fun onVkClick() {

    }

    @OnClick(R.id.sign_in_skip)
    fun onSkipClick() {
        activity!!.finish()
    }
}