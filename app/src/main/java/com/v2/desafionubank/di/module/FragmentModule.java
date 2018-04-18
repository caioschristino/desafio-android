package com.v2.desafionubank.di.module;

import android.content.Context;

import com.v2.desafionubank.di.FragmentContext;
import com.v2.desafionubank.ui.BaseFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by CaioSChristino on 20/02/2018.
 */

@Module
public class FragmentModule {
    private BaseFragment mBaseFragment;

    public FragmentModule(BaseFragment baseFragment) {
        mBaseFragment = baseFragment;
    }

    @Provides
    @FragmentContext
    Context provideContext() {
        return mBaseFragment.getContext();
    }

    @Provides
    BaseFragment provideFragment() {
        return mBaseFragment;
    }
}