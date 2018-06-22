package com.kotlin_research.kotlinresearch.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.kotlin_research.kotlinresearch.data.room.AppDatabase
import com.kotlin_research.kotlinresearch.data.room.NoteDao
import com.kotlin_research.kotlinresearch.domain.interactor.RoomService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(private var context: Context) {
    @Provides
    fun context(): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideAppDatabase(): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "database").build()
    }

    @Provides
    @Singleton
    fun provideDao(appDatabase: AppDatabase): NoteDao {
        return appDatabase.noteDao()
    }

    @Provides
    @Singleton
    fun roomService(dao: NoteDao): RoomService {
        return RoomService(dao)
    }
}