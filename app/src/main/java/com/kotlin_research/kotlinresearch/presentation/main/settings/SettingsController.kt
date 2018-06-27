package com.kotlin_research.kotlinresearch.presentation.main.settings

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.OnClick
import com.hannesdorfmann.mosby3.mvp.conductor.MvpController
import com.kotlin_research.kotlinresearch.App
import com.kotlin_research.kotlinresearch.R
import android.content.Intent
import android.net.Uri
import com.kotlin_research.kotlinresearch.presentation.auth.AuthActivity


class SettingsController : MvpController<SettingsContract.View, SettingsContract.Presenter>(), SettingsContract.View {

    override fun createPresenter(): SettingsContract.Presenter {
        return SettingsPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view: View = inflater.inflate(R.layout.controller_settings, container, false)
        ButterKnife.bind(this, view)
        App.getComponent().inject(this)
        return view
    }

    @OnClick(R.id.settings_delete_all)
    fun onDeleteAllClick() {
        AlertDialog.Builder(activity)
                .setTitle("Удаление всех записей")
                .setMessage("Вы действительно удалить все записи?")
                .setPositiveButton("Да", { dialog, _ ->
                    presenter.deleteAll()
                    dialog.cancel()
                })
                .setNegativeButton("Нет", { dialog, _ -> dialog.cancel() })
                .create()
                .show()
    }

    @OnClick(R.id.settings_version)
    fun onVersionClick() {
        val title = activity!!.getString(R.string.version)
        val text = activity!!.getString(R.string.about_version)
        AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(text)
                .setPositiveButton("Ок", { dialog, _ ->
                    dialog.cancel()
                })
                .create()
                .show()
    }

    @OnClick(R.id.settings_set_mark_in_play)
    fun onSetMarkClick() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("market://details?id=com.bignerdranch.android.OSM")
        startActivity(intent)
    }

    @OnClick(R.id.settings_auth)
    fun onAuthClick() {
        val intent = Intent(activity, AuthActivity::class.java)
        startActivity(intent)
    }
}