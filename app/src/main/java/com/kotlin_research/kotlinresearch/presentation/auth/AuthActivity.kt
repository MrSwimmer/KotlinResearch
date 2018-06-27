package com.kotlin_research.kotlinresearch.presentation.auth

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Optional
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.kotlin_research.kotlinresearch.R
import com.kotlin_research.kotlinresearch.presentation.auth.sign_in.CloudController
import com.kotlin_research.kotlinresearch.presentation.auth.sign_in.SignInController
import javax.annotation.Nullable

class AuthActivity : AppCompatActivity() {
    lateinit var router: Router

    @BindView(R.id.auth_container)
    lateinit var authFrameLayout: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        ButterKnife.bind(this)
        router = Conductor.attachRouter(this, authFrameLayout, savedInstanceState)
        if (!router.hasRootController())
            router.setRoot(RouterTransaction.with(SignInController()))
    }
}