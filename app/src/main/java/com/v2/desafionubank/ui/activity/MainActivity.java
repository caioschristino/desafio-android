package com.v2.desafionubank.ui.activity;

import com.v2.desafionubank.R;
import com.v2.desafionubank.ui.BaseMain;
import com.v2.desafionubank.ui.NavigationManager;
import com.v2.desafionubank.ui.fragment.NoticeFragment;

/**
 * Created by CaioSChristino on 24/03/18.
 */

public class MainActivity extends BaseMain {
    @Override
    public int layoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

    @Override
    public void initViews() {
        openFragment(new NoticeFragment());
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