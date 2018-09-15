package com.bignerdranch.android.osm.domain.interactor

import android.content.SharedPreferences

class SettingsService(var sharedPreferences: SharedPreferences) {

    val ERROR = "error"

    fun getSome(): String {
        return sharedPreferences.getString("some", ERROR)
    }

    fun setSome() {
        var editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("some", "some")
        editor.apply()
    }

    fun signOut() {
        var editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("vk", ERROR)
        editor.putString("twitter", ERROR)
        editor.apply()
    }

    fun isEntered(): Boolean {
        return sharedPreferences.getString("vk", ERROR) != ERROR
    }

    fun setToken(accessToken: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("vk", accessToken)
        editor.apply()
    }

    fun setPoint(points: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("point", points)
        editor.apply()
    }
}