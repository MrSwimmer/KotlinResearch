package com.bignerdranch.android.osm.di

import com.bignerdranch.android.osm.data.paging.NotePositionalDataSource
import com.bignerdranch.android.osm.di.module.FireModule
import com.bignerdranch.android.osm.di.module.RoomModule
import com.bignerdranch.android.osm.di.module.SharedPreferencesModule
import com.bignerdranch.android.osm.presentation.auth.sign_in.CloudController
import com.bignerdranch.android.osm.presentation.auth.sign_in.CloudPresenter
import com.bignerdranch.android.osm.presentation.auth.sign_in.SignInController
import com.bignerdranch.android.osm.presentation.auth.sign_in.SignInPresenter
import com.bignerdranch.android.osm.presentation.settings.NotesController
import com.bignerdranch.android.osm.presentation.settings.NotesPresenter
import com.bignerdranch.android.osm.presentation.main.settings.SettingsController
import com.bignerdranch.android.osm.presentation.main.settings.SettingsPresenter
import com.bignerdranch.android.osm.presentation.main.add_note.AddNoteController
import com.bignerdranch.android.osm.presentation.main.add_note.AddNotePresenter
import com.bignerdranch.android.osm.presentation.main.notes.recycler.NotePagingAdapter
import com.bignerdranch.android.osm.presentation.main.result_note.ResultNotePresenter
import com.bignerdranch.android.osm.presentation.main.statistic.StatisticController
import com.bignerdranch.android.osm.presentation.main.statistic.StatisticPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SharedPreferencesModule::class, RoomModule::class, FireModule::class])
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