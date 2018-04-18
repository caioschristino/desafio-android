package com.v2.desafionubank.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.v2.desafionubank.ChargebackApplication;
import com.v2.desafionubank.R;
import com.v2.desafionubank.controller.SessionController;
import com.v2.desafionubank.di.component.DaggerFragmentComponent;
import com.v2.desafionubank.di.component.FragmentComponent;
import com.v2.desafionubank.di.module.FragmentModule;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by CaioSChristino on 19/02/2018.
 */

public abstract class BaseFragment extends Fragment {
    @Inject
    NavigationManager mNavigationManager;
    @Inject
    protected SessionController mSessionController;

    FragmentComponent mFragmentComponent;
    View view;

    protected abstract void initViews();

    protected abstract int setContent();

    public abstract void doInBackFragment();

    public abstract String getTitle();

    private FragmentComponent getFragmentComponent() {
        if (mFragmentComponent == null) {
            mFragmentComponent = DaggerFragmentComponent.builder()
                    .fragmentModule(new FragmentModule(this))
                    .applicationComponent(ChargebackApplication.getApp().getApplicationComponent())
                    .build();
        }
        return mFragmentComponent;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(setContent(), null);
        ButterKnife.bind(this, view);
        getFragmentComponent().inject(this);
        initActionBar(true, getString(R.string.app_name));
        initViews();

        return view;
    }

    private void initActionBar(boolean showHomeButton, String title) {
        if (getActivity() != null && getActivity() instanceof BaseMain) {
            ActionBar supportActionBar = ((BaseMain) getActivity()).getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setHomeButtonEnabled(showHomeButton);
                supportActionBar.setTitle(title);
            }
        }
    }

    public void pushFragment(BaseFragment fragment) {
        if (mNavigationManager != null) {
            mNavigationManager.pushFragment(fragment);
        }
    }

    public void popSelf() {
        hideKeyboard();
        if (mNavigationManager != null) {
            mNavigationManager.popFragment(((BaseMain) getActivity()).setBackNavigationListener());
        }
    }

    public void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        View currentFocusedView = getActivity().getCurrentFocus();
        if (currentFocusedView != null) {
            inputManager.hideSoftInputFromWindow(currentFocusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}