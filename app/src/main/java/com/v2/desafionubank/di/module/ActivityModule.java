package com.v2.desafionubank.di.module;

import android.content.Context;

import com.v2.desafionubank.di.ActivityContext;
import com.v2.desafionubank.ui.BaseMain;

import dagger.Module;
import dagger.Provides;

/**
 * Created by CaioSChristino on 05/02/18.
 */

@Module
public class ActivityModule {
    private BaseMain mBaseMain;

    public ActivityModule(BaseMain baseMain) {
        mBaseMain = baseMain;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mBaseMain;
    }

    @Provides
    BaseMain provideActivity() {
        return mBaseMain;
    }
}
