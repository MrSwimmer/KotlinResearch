package com.bignerdranch.android.osm.presentation.watch.list

import android.os.Bundle
import com.bignerdranch.android.osm.presentation.watch.list.recycler.RecyclerViewBTAdapter
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

interface ListContract {
    interface View : MvpView {
        fun showMessage(s: String)
        fun nextFragment(b: Boolean, bundle: Bundle?)
        fun updateRecycler(adapter: RecyclerViewBTAdapter)
        fun unclickableRecycler()
    }

    interface Presenter : MvpPresenter<View> {
        fun setList()
    }
}