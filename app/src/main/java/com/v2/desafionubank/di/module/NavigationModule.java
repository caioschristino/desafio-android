package com.v2.desafionubank.di.module;

import com.v2.desafionubank.ui.NavigationManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by CaioSChristino on 20/02/2018.
 */

@Module
public class NavigationModule {

    @Provides
    @Singleton
    protected NavigationManager provideNavigationManager() {
        return new NavigationManager();
    }
}
