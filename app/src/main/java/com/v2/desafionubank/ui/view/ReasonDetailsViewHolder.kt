package com.v2.desafionubank.ui.view

import android.view.View
import android.widget.Switch
import android.widget.TextView
import com.v2.desafionubank.R
import com.v2.desafionubank.model.ReasonDetails

/**
 * Created by csanchez on 19/04/2018.
 */

class ReasonDetailsViewHolder(view: View) : BaseViewHolder(view) {
    internal lateinit var item: ReasonDetails

    init {
        val res = itemView.context.resources
        itemView.findViewById<Switch>(R.id.switch_about).setOnCheckedChangeListener { buttonView, isChecked ->
            this.item.isSelected = isChecked
            if (isChecked) {
                itemView.findViewById<Switch>(R.id.switch_about).textOn = res.getString(R.string.switch_on)
                itemView.findViewById<TextView>(R.id.about).setTextColor(itemView.context.resources.getColor(R.color.colorRed))
            } else {
                itemView.findViewById<Switch>(R.id.switch_about).textOn = res.getString(R.string.switch_off)
                itemView.findViewById<TextView>(R.id.about).setTextColor(itemView.context.resources.getColor(R.color.colorTexts))
            }
        }
    }

    fun setItem(item: ReasonDetails) {
        this.item = item
    }

    fun setText(about: String?) {
        itemView.findViewById<TextView>(R.id.about).text = about
    }
}