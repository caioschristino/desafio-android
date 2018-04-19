package com.v2.desafionubank.ui.view

import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.ButterKnife

/**
 * Created by csanchez on 19/04/2018.
 */

open class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    init {
        ButterKnife.bind(this, view)
    }
}
