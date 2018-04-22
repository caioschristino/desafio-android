package com.v2.desafionubank

import com.v2.desafionubank.di.component.ApplicationComponent
import com.v2.desafionubank.di.module.AndroidModule
import com.v2.desafionubank.di.module.NavigationModule
import com.v2.desafionubank.di.module.NetModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by CaioSChristino on 22/04/18.
 */

@Singleton
@Component(modules = arrayOf(NetModule::class, NavigationModule::class, AndroidModule::class))
interface TestAppComponent : ApplicationComponent {
    fun inject(test: ServiceInstrumentedTest)
}