package com.kotlin_research.kotlinresearch.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SharedPreferencesModule::class])
interface AppComponent {

}