package com.v2.desafionubank.ui.view

import android.app.Dialog
import android.support.v4.app.FragmentActivity
import android.view.View
import android.view.Window
import com.v2.desafionubank.R

/**
 * Created by csanchez on 19/04/2018.
 */

class ViewDialog {
    fun showDialog(activity: FragmentActivity) {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog)

        dialog.findViewById<View>(R.id.dialog_close)
                .setOnClickListener({ v ->
                    dialog.dismiss()
                })
        dialog.show()
    }
}
