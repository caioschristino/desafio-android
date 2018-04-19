package com.v2.desafionubank.di.component

import android.app.Application
import android.content.Context
import com.v2.desafionubank.ChargebackApplication
import com.v2.desafionubank.controller.SessionController
import com.v2.desafionubank.data.SharedPreferenceHelper
import com.v2.desafionubank.di.ApplicationContext
import com.v2.desafionubank.di.module.ApplicationModule
import com.v2.desafionubank.di.module.NavigationModule
import com.v2.desafionubank.di.module.NetModule
import com.v2.desafionubank.ui.NavigationManager
import dagger.Component
import javax.inject.Singleton
import dagger.BindsInstance



/**
 * Created by csanchez on 19/04/2018.
 */

@Singleton
@Component(modules = arrayOf(NetModule::class, NavigationModule::class, ApplicationModule::class))
interface ApplicationComponent {
//    @ApplicationContext
//    val context: Context

    val application: Application

    val preferenceHelper: SharedPreferenceHelper

    val sessionControler: SessionController

    val navigationManager: NavigationManager

    fun inject(app: ChargebackApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: ChargebackApplication): Builder

        fun build(): ApplicationComponent
    }
}