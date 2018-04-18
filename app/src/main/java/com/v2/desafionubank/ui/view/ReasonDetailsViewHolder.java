package com.v2.desafionubank.ui.view;

import android.content.res.Resources;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.v2.desafionubank.R;
import com.v2.desafionubank.model.ReasonDetails;

import butterknife.BindView;

/**
 * Created by csanchez on 16/04/2018.
 */

public class ReasonDetailsViewHolder extends BaseViewHolder {
    ReasonDetails item;
    @BindView(R.id.about)
    TextView mAbout;
    @BindView(R.id.switch_about)
    Switch mSwitch;

    public ReasonDetailsViewHolder(View view) {
        super(view);
        Resources res = itemView.getContext().getResources();
        mSwitch.setOnCheckedChangeListener((buttonView, isChecked) ->
                {
                    item.setSelected(isChecked);
                    if (isChecked) {
                        mSwitch.setTextOn(res.getString(R.string.switch_on));
                        mAbout.setTextColor(itemView.getContext().getResources().getColor(R.color.colorRed));
                    } else {
                        mSwitch.setTextOn(res.getString(R.string.switch_off));
                        mAbout.setTextColor(itemView.getContext().getResources().getColor(R.color.colorTexts));
                    }
                }
        );
    }

    public void setItem(ReasonDetails item) {
        this.item = item;
    }

    public void setText(String about) {
        this.mAbout.setText(about);
    }
}
