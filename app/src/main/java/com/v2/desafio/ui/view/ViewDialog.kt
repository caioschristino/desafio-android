package com.v2.desafio.ui.view

import android.app.Dialog
import android.support.v4.app.FragmentActivity
import android.view.View
import android.view.Window
import com.v2.desafio.R

/**
 * Created by csanchez on 19/04/2018.
 */

class ViewDialog {
    interface ViewDialogListener {
        fun onDismiss()
    }


    fun showDialog(activity: FragmentActivity, listener: ViewDialogListener) {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog)

        dialog.findViewById<View>(R.id.dialog_close)
                .setOnClickListener({ v ->
                    dialog.dismiss()
                    listener.onDismiss()
                })
        dialog.show()
    }
}
