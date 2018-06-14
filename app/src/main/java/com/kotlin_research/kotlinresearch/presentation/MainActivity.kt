package com.kotlin_research.kotlinresearch.presentation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import butterknife.BindView
import butterknife.ButterKnife
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.kotlin_research.kotlinresearch.R

class MainActivity : AppCompatActivity() {

    lateinit var router: Router

    @BindView(R.id.main_container)
    lateinit var mainFrameLayout: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        router = Conductor.attachRouter(this, mainFrameLayout, savedInstanceState)
        if (!router.hasRootController())
            router.setRoot(RouterTransaction.with())
    }
}
