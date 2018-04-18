package com.v2.desafionubank.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.v2.desafionubank.ChargebackApplication;
import com.v2.desafionubank.R;
import com.v2.desafionubank.di.component.ActivityComponent;
import com.v2.desafionubank.di.component.DaggerActivityComponent;
import com.v2.desafionubank.di.module.ActivityModule;
import com.v2.desafionubank.model.ProcessRequest;
import com.v2.desafionubank.model.ResponseError;
import com.v2.desafionubank.ui.activity.ErrorActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;


/**
 * Created by CaioSChristino on 05/02/18.
 */

public abstract class BaseMain extends AppCompatActivity {
    protected final String ERROR_MESSAGE = "error_message";
    @Inject
    NavigationManager mNavigationManager;

    @BindView(R.id.progress)
    @Nullable
    RelativeLayout mProgressBar;

    ActivityComponent mActivityComponent;

    public abstract @LayoutRes
    int layoutResID();

    public abstract void initViews();

    public abstract NavigationManager.BackFragmentNavigationListener setBackNavigationListener();

    private ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(ChargebackApplication.getApp().getApplicationComponent())
                    .build();
        }
        return mActivityComponent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResID());
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        mNavigationManager.init(getSupportFragmentManager());

        ((ChargebackApplication) getApplication())
                .getBus()
                .toObservable()
                .subscribe((Action1<? super Object>) object -> {
                    if (object instanceof ResponseError) {
                        ResponseError error = (ResponseError) object;
                        Intent intent = new Intent(this, ErrorActivity.class);
                        intent.putExtra(ERROR_MESSAGE, error.getError());
                        startActivity(intent);
                    } else if (object instanceof ProcessRequest) {
                        ProcessRequest process = (ProcessRequest) object;
                        if (mProgressBar != null) {
                            mProgressBar.setVisibility(process.isShowProcess() ? View.VISIBLE : View.GONE);
                        }
                    }
                });
        initViews();
    }

    public void openFragment(BaseFragment fragment) {
        mNavigationManager.pushFragment(fragment);
    }

    @Override
    public void onBackPressed() {
        mNavigationManager.popFragment(setBackNavigationListener());
    }
}
