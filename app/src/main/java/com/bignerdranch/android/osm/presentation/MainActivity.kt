package com.bignerdranch.android.osm.presentation

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.util.Log
import android.widget.FrameLayout
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.bignerdranch.android.osm.App
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bignerdranch.android.osm.R
import com.bignerdranch.android.osm.domain.interactor.ApiService
import com.bignerdranch.android.osm.domain.interactor.SettingsService
import com.bignerdranch.android.osm.presentation.auth.AuthController
import com.bignerdranch.android.osm.presentation.profile.ProfileController
import com.bignerdranch.android.osm.presentation.settings.NotesController
import com.bignerdranch.android.osm.presentation.settings.SettingsController
import com.bignerdranch.android.osm.presentation.statistic.StatisticController
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKError
import com.vk.sdk.util.VKUtil
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    lateinit var router: Router

    @BindView(R.id.main_container)
    lateinit var mainFrameLayout: FrameLayout
    @BindView(R.id.main_bottom_navigation)
    lateinit var bottomNavigationView: BottomNavigationView

    @Inject
    lateinit var settingsService: SettingsService
    @Inject
    lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        App.getComponent().inject(this)
        router = Conductor.attachRouter(this, mainFrameLayout, savedInstanceState)
        if (!settingsService.isEntered()) {
            if (!router.hasRootController())
                router.setRoot(RouterTransaction.with(AuthController()))
        } else {
            ButterKnife.bind(this)

            if (!router.hasRootController())
                router.setRoot(RouterTransaction.with(StatisticController()))
            bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        }
        //val fingerprints = VKUtil.getCertificateFingerprint(this, this.packageName)
        //Log.i("code", fingerprints[0])

    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        if (item.itemId != bottomNavigationView.selectedItemId) {
            when (item.itemId) {
                R.id.navigation_action_notes -> {
                    router.replaceTopController(RouterTransaction.with(NotesController()))
                }
                R.id.navigation_action_statistic -> {
                    router.replaceTopController(RouterTransaction.with(StatisticController()))
                }
                R.id.navigation_action_settings -> {
                    router.replaceTopController(RouterTransaction.with(SettingsController()))
                }
                R.id.navigation_action_profile -> {
                    router.replaceTopController(RouterTransaction.with(ProfileController()))
                }
            }
        }
        true
    }

    override fun onBackPressed() {
        if (!router.handleBack())
            super.onBackPressed()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.i("code", "onRes $resultCode $data")
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, object : VKCallback<VKAccessToken> {
                    override fun onResult(res: VKAccessToken?) {
                        Log.i("code", "success " + res!!.accessToken)
                        settingsService.setToken(res.accessToken)
                        apiService.getPoint(res.userId, res.accessToken, object : ApiService.PointCallback {
                            override fun onSuccess(points: String) {
                                Log.i("code", "success $points")
                                settingsService.setPoint(points)
                                router.setRoot(RouterTransaction.with(StatisticController()))
                            }

                            override fun onError(it: Throwable) {
                                Log.i("code", "post error ${it.message}")
                                Toast.makeText(baseContext, it.message, Toast.LENGTH_SHORT).show()
                            }

                        })
                    }

                    override fun onError(error: VKError?) {
                        Log.i("code", "error " + error!!.errorMessage)
                        Toast.makeText(baseContext, error.errorMessage, Toast.LENGTH_SHORT).show()
                    }
                })) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
