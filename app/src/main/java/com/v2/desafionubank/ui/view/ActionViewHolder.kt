package com.v2.desafionubank.ui.view

import android.view.View
import android.widget.TextView
import com.v2.desafionubank.R
import com.v2.desafionubank.model.NoticeAction

/**
 * Created by csanchez on 19/04/2018.
 */

class ActionViewHolder(view: View) : BaseViewHolder(view) {
    fun setTitle(item: NoticeAction) {
        itemView.findViewById<TextView>(R.id.title_item).text = item.title!!.toUpperCase()
        if (item.action == "continue") {
            itemView.findViewById<TextView>(R.id.title_item).setTextColor(itemView.context.resources.getColor(R.color.colorEnablePurple))
        }
    }

    fun setOnclick(onclick: View.OnClickListener) {
        if (onclick != null) {
            itemView.setOnClickListener(onclick)
        }
    }
}