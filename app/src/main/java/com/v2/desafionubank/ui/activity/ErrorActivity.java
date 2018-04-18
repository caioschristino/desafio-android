package com.v2.desafionubank.ui.activity;


import android.os.Bundle;
import android.widget.TextView;

import com.v2.desafionubank.R;
import com.v2.desafionubank.ui.BaseMain;
import com.v2.desafionubank.ui.NavigationManager;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by CaioSChristino on 18/04/18.
 */

public class ErrorActivity extends BaseMain {
    @BindView(R.id.about_error)
    TextView mAboutError;

    @Override
    public int layoutResID() {
        return R.layout.activity_erro;
    }

    @Override
    public void initViews() {
        Bundle b = getIntent().getExtras();
        String error = b.getString(ERROR_MESSAGE);
        mAboutError.setText(error);
    }

    @OnClick(R.id.close_btn)
    public void onClickClose() {
        onBackPressed();
    }

    @Override
    public NavigationManager.BackFragmentNavigationListener setBackNavigationListener() {
        return new NavigationManager.BackFragmentNavigationListener() {
            @Override
            public void doInFinishBackNavigation() {
                finish();
            }
        };
    }
}
