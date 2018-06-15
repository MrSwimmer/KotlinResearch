package com.kotlin_research.kotlinresearch.di

import com.kotlin_research.kotlinresearch.presentation.settings.NotesController
import com.kotlin_research.kotlinresearch.presentation.settings.NotesPresenter
import com.kotlin_research.kotlinresearch.presentation.settings.SettingsController
import com.kotlin_research.kotlinresearch.presentation.settings.SettingsPresenter
import com.kotlin_research.kotlinresearch.presentation.single_note.SingleNoteController
import com.kotlin_research.kotlinresearch.presentation.single_note.SingleNotePresenter
import com.kotlin_research.kotlinresearch.presentation.statistic.StatisticController
import com.kotlin_research.kotlinresearch.presentation.statistic.StatisticPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SharedPreferencesModule::class])
interface AppComponent {
    fun inject(notesController: NotesController)
    fun inject(notesController: NotesPresenter)
    fun inject(settingsController: SettingsController)
    fun inject(settingsPresenter: SettingsPresenter)
    fun inject(statisticController: StatisticController)
    fun inject(statisticPresenter: StatisticPresenter)
    fun inject(singleNoteController: SingleNoteController)
    fun inject(singleNotePresenter: SingleNotePresenter)
}