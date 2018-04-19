package com.v2.desafionubank.ui.view

import android.view.View
import android.widget.Switch
import android.widget.TextView
import butterknife.BindView
import com.v2.desafionubank.R
import com.v2.desafionubank.model.ReasonDetails

/**
 * Created by csanchez on 19/04/2018.
 */

class ReasonDetailsViewHolder(view: View) : BaseViewHolder(view) {
    internal lateinit var item: ReasonDetails
    @BindView(R.id.about)
    internal var mAbout: TextView? = null
    @BindView(R.id.switch_about)
    internal var mSwitch: Switch? = null

    init {
        val res = itemView.context.resources
        mSwitch!!.setOnCheckedChangeListener { buttonView, isChecked ->
            item.isSelected = isChecked
            if (isChecked) {
                mSwitch!!.textOn = res.getString(R.string.switch_on)
                mAbout!!.setTextColor(itemView.context.resources.getColor(R.color.colorRed))
            } else {
                mSwitch!!.textOn = res.getString(R.string.switch_off)
                mAbout!!.setTextColor(itemView.context.resources.getColor(R.color.colorTexts))
            }
        }
    }

    fun setItem(item: ReasonDetails) {
        this.item = item
    }

    fun setText(about: String?) {
        this.mAbout!!.text = about
    }
}