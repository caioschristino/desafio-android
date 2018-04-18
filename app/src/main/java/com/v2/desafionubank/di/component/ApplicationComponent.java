package com.v2.desafionubank.di.component;

import android.app.Application;
import android.content.Context;

import com.v2.desafionubank.ChargebackApplication;
import com.v2.desafionubank.controller.SessionController;
import com.v2.desafionubank.data.SharedPreferenceHelper;
import com.v2.desafionubank.di.ApplicationContext;
import com.v2.desafionubank.di.module.ApplicationModule;
import com.v2.desafionubank.di.module.NavigationModule;
import com.v2.desafionubank.di.module.NetModule;
import com.v2.desafionubank.ui.NavigationManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by CaioSChristino on 05/02/18.
 */

@Singleton
@Component(modules = {NetModule.class, NavigationModule.class, ApplicationModule.class})
public interface ApplicationComponent {
    void inject(ChargebackApplication app);

    @ApplicationContext
    Context getContext();

    Application getApplication();

    SharedPreferenceHelper getPreferenceHelper();

    SessionController getSessionControler();

    NavigationManager getNavigationManager();
}
