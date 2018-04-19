package com.v2.desafionubank.ui.view

import android.view.View
import android.widget.TextView
import butterknife.BindView
import com.v2.desafionubank.R
import com.v2.desafionubank.model.NoticeAction

/**
 * Created by csanchez on 19/04/2018.
 */

class ActionViewHolder(view: View) : BaseViewHolder(view) {
    @BindView(R.id.title_item)
    internal var title: TextView? = null

    fun setTitle(item: NoticeAction) {
        this.title!!.text = item.title!!.toUpperCase()
        if (item.action == "continue") {
            this.title!!.setTextColor(itemView.context.resources.getColor(R.color.colorEnablePurple))
        }
    }

    fun setOnclick(onclick: View.OnClickListener) {
        itemView.setOnClickListener(onclick)
    }
}