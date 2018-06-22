package com.kotlin_research.kotlinresearch.domain.interactor

import android.content.SharedPreferences

class SettingsService(var sharedPreferences: SharedPreferences) {

    fun getSome(): String {
        return sharedPreferences.getString("some", "error")
    }

    fun setSome() {
        var editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("some", "some")
        editor.apply()
    }
}