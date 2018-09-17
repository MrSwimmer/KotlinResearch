package com.bignerdranch.android.osm.domain.interactor

import com.bignerdranch.android.osm.data.api.GraphApi
import com.bignerdranch.android.osm.data.model.Points
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ApiServiceGraph(var api: GraphApi) {
    fun setPoints(points: Points, callback: PointsCallback) {
        api.setPoint(points)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ callback.onSuccess(it.points) }, { callback.onError(it) })
    }

    fun getPoints(callback: PointsCallback) {
        api.getPoint()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ callback.onSuccess(it.points) }, { callback.onError(it) })
    }

    interface PointsCallback {
        fun onSuccess(it: Float)
        fun onError(it: Throwable)
    }
}