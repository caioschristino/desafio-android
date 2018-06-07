package com.v2.desafio.di.component

import com.v2.desafio.di.PerActivity
import com.v2.desafio.di.module.ActivityModule
import com.v2.desafio.ui.BaseMain
import dagger.Component

/**
 * Created by csanchez on 19/04/2018.
 */

@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(mBaseMain: BaseMain)
}
