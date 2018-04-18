package com.v2.desafionubank.ui.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by CaioSChristino on 30/03/18.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
