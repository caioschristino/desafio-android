package com.v2.desafio.di.component

import com.v2.desafio.di.PerFragment
import com.v2.desafio.di.module.FragmentModule
import com.v2.desafio.ui.BaseFragment
import dagger.Component


/**
 * Created by csanchez on 19/04/2018.
 */

@PerFragment
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(FragmentModule::class))
interface FragmentComponent {
    fun inject(mFragment: BaseFragment)

}
