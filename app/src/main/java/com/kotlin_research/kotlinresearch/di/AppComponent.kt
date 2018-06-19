package com.kotlin_research.kotlinresearch.di

import com.kotlin_research.kotlinresearch.data.paging.NotePositionalDataSource
import com.kotlin_research.kotlinresearch.di.module.RoomModule
import com.kotlin_research.kotlinresearch.di.module.SharedPreferencesModule
import com.kotlin_research.kotlinresearch.presentation.settings.NotesController
import com.kotlin_research.kotlinresearch.presentation.settings.NotesPresenter
import com.kotlin_research.kotlinresearch.presentation.settings.SettingsController
import com.kotlin_research.kotlinresearch.presentation.settings.SettingsPresenter
import com.kotlin_research.kotlinresearch.presentation.add_note.AddNoteController
import com.kotlin_research.kotlinresearch.presentation.add_note.AddNotePresenter
import com.kotlin_research.kotlinresearch.presentation.notes.recycler.NotePagingAdapter
import com.kotlin_research.kotlinresearch.presentation.result_note.ResultNotePresenter
import com.kotlin_research.kotlinresearch.presentation.statistic.StatisticController
import com.kotlin_research.kotlinresearch.presentation.statistic.StatisticPresenter
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
}