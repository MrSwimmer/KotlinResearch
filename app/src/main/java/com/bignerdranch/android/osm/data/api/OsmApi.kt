package com.bignerdranch.android.osm.data.api

import com.bignerdranch.android.osm.data.model.User
import com.bignerdranch.android.osm.data.model.ResponsePoint
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

interface OsmApi {

    @POST("/")
    fun getPoint(@Body user: User): Observable<ResponsePoint>
}