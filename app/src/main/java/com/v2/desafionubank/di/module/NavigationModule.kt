package com.v2.desafionubank.di.module

import com.v2.desafionubank.ui.NavigationManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by csanchez on 19/04/2018.
 */

@Module
class NavigationModule {
    @Provides
    @Singleton
    protected fun provideNavigationManager(): NavigationManager {
        return NavigationManager()
    }
}