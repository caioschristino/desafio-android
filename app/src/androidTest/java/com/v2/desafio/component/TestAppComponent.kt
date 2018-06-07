package com.v2.desafio.component

import com.v2.desafio.ServiceInstrumentedTest
import com.v2.desafio.controller.SessionController
import com.v2.desafio.di.module.AndroidModule
import com.v2.desafio.module.TestNetModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by CaioSChristino on 22/04/18.
 */

@Singleton
@Component(modules = arrayOf(TestNetModule::class, AndroidModule::class))
interface TestAppComponent {
    fun inject(test: ServiceInstrumentedTest)

    val mSession: SessionController
}