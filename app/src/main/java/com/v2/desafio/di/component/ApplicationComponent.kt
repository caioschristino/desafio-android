package com.v2.desafio.di.component

import com.v2.desafio.ChargebackApplication
import com.v2.desafio.controller.SessionController
import com.v2.desafio.data.SharedPreferenceHelper
import com.v2.desafio.di.module.AndroidModule
import com.v2.desafio.di.module.NavigationModule
import com.v2.desafio.di.module.NetModule
import com.v2.desafio.ui.BaseFragment
import com.v2.desafio.ui.BaseMain
import com.v2.desafio.ui.NavigationManager
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
