package com.bignerdranch.android.osm.di.module

import android.content.Context
import android.content.SharedPreferences
import com.bignerdranch.android.osm.domain.interactor.SettingsService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPreferencesModule(private var context: Context) {

    @Provides
    fun context(): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences {
        return context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun settingsService(sharedPreferences: SharedPreferences): SettingsService {
        return SettingsService(sharedPreferences)
    }

}