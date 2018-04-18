package com.v2.desafionubank.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.v2.desafionubank.di.ApplicationContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by CaioSChristino on 05/02/18.
 */
@Module
public class ApplicationModule {
    private final Application mApplication;

    public ApplicationModule(Application app) {
        mApplication = app;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    SharedPreferences provideSharedPrefs() {
        return mApplication.getSharedPreferences("appEngravidei-prefs", Context.MODE_PRIVATE);
    }

    @Provides
    String provideDatabaseName() {
        return "engravidei.db";
    }
}
