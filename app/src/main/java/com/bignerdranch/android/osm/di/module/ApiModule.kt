package com.bignerdranch.android.osm.di.module

import com.bignerdranch.android.osm.data.api.OsmApi
import com.bignerdranch.android.osm.domain.interactor.ApiService
import dagger.Module
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import javax.inject.Singleton
import dagger.Provides


@Module
class ApiModule {
    @Provides
    @Singleton
    fun providesService(): ApiService {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.1.55:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        val memeBattleApi = retrofit.create(OsmApi::class.java)
        return ApiService(memeBattleApi)
    }
}