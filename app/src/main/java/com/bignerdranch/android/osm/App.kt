package com.bignerdranch.android.osm

import android.app.Application
import com.bignerdranch.android.osm.di.AppComponent
import com.bignerdranch.android.osm.di.DaggerAppComponent
import com.bignerdranch.android.osm.di.module.RoomModule
import com.bignerdranch.android.osm.di.module.SharedPreferencesModule
import com.vk.sdk.VKSdk

class App : Application() {

    companion object {
        private lateinit var component: AppComponent
        fun getComponent(): AppComponent {
            return component
        }
    }

    override fun onCreate() {
        super.onCreate()
        VKSdk.initialize(applicationContext)
        component = DaggerAppComponent.builder()
                .sharedPreferencesModule(SharedPreferencesModule(applicationContext))
                .roomModule(RoomModule(applicationContext))
                .build()
    }
}