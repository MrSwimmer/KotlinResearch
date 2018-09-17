package com.bignerdranch.android.osm.data.api

import com.bignerdranch.android.osm.data.model.Points
import com.bignerdranch.android.osm.data.model.User
import com.bignerdranch.android.osm.data.model.ResponsePoint
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import rx.Observable

interface GraphApi {

    @POST("/")
    fun setPoint(@Body points: Points): Observable<Points>
    @GET("/")
    fun getPoint(): Observable<Points>
}