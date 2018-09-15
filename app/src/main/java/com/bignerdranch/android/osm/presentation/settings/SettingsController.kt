package com.bignerdranch.android.osm.presentation.settings

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.OnClick
import com.hannesdorfmann.mosby3.mvp.conductor.MvpController
import com.bignerdranch.android.osm.App
import com.bignerdranch.android.osm.R
import android.content.Intent
import android.net.Uri
import android.widget.TextView
import butterknife.BindView
import com.bignerdranch.android.osm.domain.interactor.SettingsService
import com.bignerdranch.android.osm.presentation.auth.AuthController
import com.bluelinelabs.conductor.RouterTransaction
import javax.inject.Inject
import com.vk.sdk.VKScope


class SettingsController : MvpController<SettingsContract.View, SettingsContract.Presenter>(), SettingsContract.View {
    init {
        App.getComponent().inject(this)
    }

    var entered = false
    override fun createPresenter(): SettingsContract.Presenter {
        return SettingsPresenter()
    }

    @Inject
    lateinit var settingsService: SettingsService

    @BindView(R.id.settings_auth)
    lateinit var auth: TextView

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
        val scope = arrayOf(VKScope.EMAIL)
        //VKSdk.login(activity!!, scope[0])
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
        signOut()
    }

    private fun signOut() {
        AlertDialog.Builder(activity)
                .setTitle("Выход из аккаунта")
                .setMessage("Вы действительно выйти из аккаунта?")
                .setPositiveButton("Да", { dialog, _ ->
                    settingsService.signOut()
                    dialog.cancel()
                    router.setRoot(RouterTransaction.with(AuthController()))
                })
                .setNegativeButton("Нет", { dialog, _ -> dialog.cancel() })
                .create()
                .show()
    }

}