package com.v2.desafionubank.ui.view;

import android.view.View;
import android.widget.TextView;

import com.v2.desafionubank.R;
import com.v2.desafionubank.model.NoticeAction;

import butterknife.BindView;

/**
 * Created by CaioSChristino on 10/04/18.
 */

public class ActionViewHolder extends BaseViewHolder {
    @BindView(R.id.title_item)
    TextView title;

    public ActionViewHolder(View view) {
        super(view);
    }

    public void setTitle(NoticeAction item) {
        this.title.setText(item.getTitle().toUpperCase());
        if (item.getAction().equals("continue")) {
            this.title.setTextColor(itemView.getContext().getResources().getColor(R.color.colorEnablePurple));
        }
    }

    public void setOnclick(View.OnClickListener onclick) {
        itemView.setOnClickListener(onclick);
    }
}