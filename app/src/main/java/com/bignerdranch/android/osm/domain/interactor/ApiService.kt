package com.bignerdranch.android.osm.domain.interactor

import com.bignerdranch.android.osm.data.model.User
import com.bignerdranch.android.osm.data.api.OsmApi
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ApiService(var api: OsmApi) {
    fun getPoint(id: String, token: String, callback: PointCallback) {
        api.getPoint(User(token, id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ callback.onSuccess(it.result) }, { callback.onError(it) })
    }

    interface PointCallback {
        fun onSuccess(points: String)
        fun onError(it: Throwable)
    }
}