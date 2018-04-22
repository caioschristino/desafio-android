package com.v2.desafionubank.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.v2.desafionubank.di.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by csanchez on 19/04/2018.
 */

@Module
open class AndroidModule(private val mApplication: Application) {
    @Provides
    @Singleton
    @ApplicationContext
    internal fun provideContext(): Context {
        return mApplication
    }

    @Provides
    internal fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    internal fun provideSharedPrefs(): SharedPreferences {
        return mApplication.getSharedPreferences("appNuBank-prefs", Context.MODE_PRIVATE)
    }
}