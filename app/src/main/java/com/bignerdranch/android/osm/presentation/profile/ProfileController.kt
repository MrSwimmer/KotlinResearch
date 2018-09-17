package com.bignerdranch.android.osm.presentation.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.BuildConfig
import com.bignerdranch.android.osm.R
import com.hannesdorfmann.mosby3.mvp.conductor.MvpController
import okhttp3.OkHttpClient

class ProfileController: MvpController<ProfileContract.View, ProfileContract.Presenter>(), ProfileContract.View {
    override fun createPresenter(): ProfileContract.Presenter {
        return ProfilePresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.controller_profile, container, false)
        val client = setupApollo()
        //client.query(SampleQuery)
        return view
    }

    private fun setupApollo(): ApolloClient {
        val okHttp = OkHttpClient
                .Builder()
                .addInterceptor({ chain ->
                    val original = chain.request()
                    val builder = original.newBuilder().method(original.method(),
                            original.body())
                    chain.proceed(builder.build())
                })
                .build()
        return ApolloClient.builder()
                .serverUrl("http://172.16.12.107:8080/query")
                .okHttpClient(okHttp)
                .build()
    }
}