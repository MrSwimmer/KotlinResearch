package com.kotlin_research.kotlinresearch.di

import com.kotlin_research.kotlinresearch.data.paging.NotePositionalDataSource
import com.kotlin_research.kotlinresearch.di.module.RoomModule
import com.kotlin_research.kotlinresearch.di.module.SharedPreferencesModule
import com.kotlin_research.kotlinresearch.presentation.auth.sign_in.CloudController
import com.kotlin_research.kotlinresearch.presentation.auth.sign_in.CloudPresenter
import com.kotlin_research.kotlinresearch.presentation.auth.sign_in.SignInController
import com.kotlin_research.kotlinresearch.presentation.auth.sign_in.SignInPresenter
import com.kotlin_research.kotlinresearch.presentation.settings.NotesController
import com.kotlin_research.kotlinresearch.presentation.settings.NotesPresenter
import com.kotlin_research.kotlinresearch.presentation.main.settings.SettingsController
import com.kotlin_research.kotlinresearch.presentation.main.settings.SettingsPresenter
import com.kotlin_research.kotlinresearch.presentation.main.add_note.AddNoteController
import com.kotlin_research.kotlinresearch.presentation.main.add_note.AddNotePresenter
import com.kotlin_research.kotlinresearch.presentation.main.notes.recycler.NotePagingAdapter
import com.kotlin_research.kotlinresearch.presentation.main.result_note.ResultNotePresenter
import com.kotlin_research.kotlinresearch.presentation.main.statistic.StatisticController
import com.kotlin_research.kotlinresearch.presentation.main.statistic.StatisticPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SharedPreferencesModule::class, RoomModule::class])
interface AppComponent {
    fun inject(notesController: NotesController)
    fun inject(notesController: NotesPresenter)
    fun inject(settingsController: SettingsController)
    fun inject(settingsPresenter: SettingsPresenter)
    fun inject(statisticController: StatisticController)
    fun inject(statisticPresenter: StatisticPresenter)
    fun inject(addNoteController: AddNoteController)
    fun inject(addNotePresenter: AddNotePresenter)
    fun inject(resultNotePresenter: ResultNotePresenter)
    fun inject(notePagingAdapter: NotePagingAdapter)
    fun inject(notePositionalDataSource: NotePositionalDataSource)
    fun inject(cloudController: CloudController)
    fun inject(cloudPresenter: CloudPresenter)
    fun inject(signInPresenter: SignInPresenter)
    fun inject(signInController: SignInController)
}