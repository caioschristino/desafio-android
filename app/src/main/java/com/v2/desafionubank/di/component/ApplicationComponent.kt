package com.v2.desafionubank.di.component

import com.v2.desafionubank.ChargebackApplication
import com.v2.desafionubank.controller.SessionController
import com.v2.desafionubank.data.SharedPreferenceHelper
import com.v2.desafionubank.di.module.AndroidModule
import com.v2.desafionubank.di.module.NavigationModule
import com.v2.desafionubank.di.module.NetModule
import com.v2.desafionubank.ui.BaseFragment
import com.v2.desafionubank.ui.BaseMain
import com.v2.desafionubank.ui.NavigationManager
import dagger.Component
import javax.inject.Singleton

/**
 * Created by csanchez on 19/04/2018.
 */

@Singleton
@Component(modules = arrayOf(NetModule::class, NavigationModule::class, AndroidModule::class))
interface ApplicationComponent {
    fun inject(application: ChargebackApplication)

    fun inject(application: BaseMain)

    fun inject(application: BaseFragment)

    val preferenceHelper: SharedPreferenceHelper

    val sessionControler: SessionController

    var navigationManager: NavigationManager
}
