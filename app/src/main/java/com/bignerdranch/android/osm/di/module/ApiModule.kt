package com.bignerdranch.android.osm.di.module

import com.bignerdranch.android.osm.data.api.GraphApi
import com.bignerdranch.android.osm.data.api.OsmApi
import com.bignerdranch.android.osm.domain.interactor.ApiService
import com.bignerdranch.android.osm.domain.interactor.ApiServiceGraph
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
                .baseUrl("http://172.16.12.111:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        val memeBattleApi = retrofit.create(OsmApi::class.java)
        return ApiService(memeBattleApi)
    }

    @Provides
    @Singleton
    fun providesServiceGraph(): ApiServiceGraph {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://172.16.12.107:3030/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        val memeBattleApi = retrofit.create(GraphApi::class.java)
        return ApiServiceGraph(memeBattleApi)
    }
}