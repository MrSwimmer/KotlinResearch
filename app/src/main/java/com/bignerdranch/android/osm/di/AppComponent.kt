package com.bignerdranch.android.osm.di

import com.bignerdranch.android.osm.di.module.ApiModule
import com.bignerdranch.android.osm.presentation.notes.paging.NotePositionalDataSource
import com.bignerdranch.android.osm.di.module.FireModule
import com.bignerdranch.android.osm.di.module.RoomModule
import com.bignerdranch.android.osm.di.module.SharedPreferencesModule
import com.bignerdranch.android.osm.presentation.MainActivity
import com.bignerdranch.android.osm.presentation.sign_in.SignInPresenter
import com.bignerdranch.android.osm.presentation.settings.NotesController
import com.bignerdranch.android.osm.presentation.settings.NotesPresenter
import com.bignerdranch.android.osm.presentation.settings.SettingsController
import com.bignerdranch.android.osm.presentation.settings.SettingsPresenter
import com.bignerdranch.android.osm.presentation.add_note.AddNoteController
import com.bignerdranch.android.osm.presentation.add_note.AddNotePresenter
import com.bignerdranch.android.osm.presentation.auth.AuthPresenter
import com.bignerdranch.android.osm.presentation.new_cloud.NewCloudController
import com.bignerdranch.android.osm.presentation.new_cloud.NewCloudPresenter
import com.bignerdranch.android.osm.presentation.notes.recycler.NotePagingAdapter
import com.bignerdranch.android.osm.presentation.result_note.ResultNotePresenter
import com.bignerdranch.android.osm.presentation.statistic.StatisticController
import com.bignerdranch.android.osm.presentation.statistic.StatisticPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SharedPreferencesModule::class, RoomModule::class, FireModule::class, ApiModule::class])
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
    fun inject(signInPresenter: SignInPresenter)
    fun inject(newCloudController: NewCloudController)
    fun inject(newCloudPresenter: NewCloudPresenter)
    fun inject(mainActivity: MainActivity)
    fun inject(authPresenter: AuthPresenter)
}