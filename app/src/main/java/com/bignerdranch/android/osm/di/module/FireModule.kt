package com.bignerdranch.android.osm.di.module

import com.bignerdranch.android.osm.domain.interactor.FireService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FireModule {
    @Provides
    @Singleton
    internal fun providesService(): FireService {
        return FireService()
    }
}