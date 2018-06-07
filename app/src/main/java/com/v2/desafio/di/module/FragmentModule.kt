package com.v2.desafio.di.module

import android.content.Context
import com.v2.desafio.di.FragmentContext
import com.v2.desafio.ui.BaseFragment
import dagger.Module
import dagger.Provides



/**
 * Created by csanchez on 19/04/2018.
 */

@Module
class FragmentModule(private val mBaseFragment: BaseFragment) {

    @Provides
    @FragmentContext
    internal fun provideContext(): Context {
        return mBaseFragment.context
    }

    @Provides
    internal fun provideFragment(): BaseFragment {
        return mBaseFragment
    }
}
