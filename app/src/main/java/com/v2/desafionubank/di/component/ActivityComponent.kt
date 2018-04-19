package com.v2.desafionubank.di.component

import com.v2.desafionubank.di.PerActivity
import com.v2.desafionubank.di.module.ActivityModule
import com.v2.desafionubank.ui.BaseMain
import dagger.Component

/**
 * Created by csanchez on 19/04/2018.
 */

@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(mBaseMain: BaseMain)
}
