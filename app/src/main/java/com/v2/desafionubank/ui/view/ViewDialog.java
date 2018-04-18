package com.v2.desafionubank.ui.view;

import android.app.Dialog;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.v2.desafionubank.R;

/**
 * Created by csanchez on 17/04/2018.
 */

public class ViewDialog {
    public interface ViewDialogListener {
        void onDismiss();
    }

    public void showDialog(FragmentActivity activity, ViewDialogListener listener) {
        Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog);

        dialog.findViewById(R.id.dialog_close)
                .setOnClickListener(v -> {
                    dialog.dismiss();
                    listener.onDismiss();
                });
        dialog.show();
    }
}
