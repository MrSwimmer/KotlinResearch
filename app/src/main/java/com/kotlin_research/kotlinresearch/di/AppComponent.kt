package com.kotlin_research.kotlinresearch.di

import com.kotlin_research.kotlinresearch.presentation.settings.SettingsController
import com.kotlin_research.kotlinresearch.presentation.settings.SettingsPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SharedPreferencesModule::class])
interface AppComponent {
    fun inject(settingsPresenter: SettingsController)
    fun inject(settingsPresenter: SettingsPresenter)
}