package com.kotlin_research.kotlinresearch.presentation.settings

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.hannesdorfmann.mosby3.mvp.conductor.MvpController
import com.kotlin_research.kotlinresearch.App
import com.kotlin_research.kotlinresearch.R

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
                    run {
                        presenter.deleteAll()
                        dialog.cancel()
                    }
                })
                .setNegativeButton("Нет", { dialog, _ -> dialog.cancel() })
                .create()
                .show()
    }
}