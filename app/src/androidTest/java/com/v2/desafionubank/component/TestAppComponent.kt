package com.v2.desafionubank.component

import com.v2.desafionubank.ServiceInstrumentedTest
import com.v2.desafionubank.api.NuMobileApi
import com.v2.desafionubank.controller.SessionController
import com.v2.desafionubank.module.TestNetModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by CaioSChristino on 22/04/18.
 */

@Singleton
@Component(modules = arrayOf(TestNetModule::class))
interface TestAppComponent {
    fun inject(test: ServiceInstrumentedTest)

    val mNuMobileApi: NuMobileApi
}