package com.v2.desafio.di.module

import android.content.Context
import com.v2.desafio.di.ActivityContext
import com.v2.desafio.ui.BaseMain
import dagger.Module
import dagger.Provides



/**
 * Created by csanchez on 19/04/2018.
 */

@Module
class ActivityModule(private val mBaseMain: BaseMain) {

    @Provides
    @ActivityContext
    internal fun provideContext(): Context {
        return mBaseMain
    }

    @Provides
    internal fun provideActivity(): BaseMain {
        return mBaseMain
    }
}
