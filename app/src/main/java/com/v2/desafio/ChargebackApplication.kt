package com.v2.desafio

import android.app.Application
import com.v2.desafio.di.component.ApplicationComponent
import com.v2.desafio.di.component.DaggerApplicationComponent
import com.v2.desafio.di.module.AndroidModule

/**
 * Created by csanchez on 19/04/2018.
 */

open class ChargebackApplication : Application() {
    var bus: RxBus? = null
        private set
    lateinit var applicationComponent: ApplicationComponent
        set

    init {
        app = this
    }

    protected fun initializeAppComponent(): ApplicationComponent {
        return DaggerApplicationComponent
                .builder()
                .androidModule(AndroidModule(this))
                .build()
        applicationComponent.inject(this)
    }

    override fun onCreate() {
        super.onCreate()
        bus = RxBus()
        app = this
        applicationComponent = initializeAppComponent()
    }

    companion object {
        @get:Synchronized
        var app: ChargebackApplication? = null
            private set
    }
}