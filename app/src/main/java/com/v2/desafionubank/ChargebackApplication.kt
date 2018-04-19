package com.v2.desafionubank

import android.app.Application
import com.v2.desafionubank.di.component.ApplicationComponent
import com.v2.desafionubank.di.module.ApplicationModule

/**
 * Created by csanchez on 19/04/2018.
 */

class ChargebackApplication : Application() {
    var bus: RxBus? = null
        private set
    lateinit var applicationComponent: ApplicationComponent
        protected set

    init {
        app = this
    }

    override fun onCreate() {
        super.onCreate()
        bus = RxBus()
        app = this
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
        applicationComponent.inject(this)
    }

    companion object {
        @get:Synchronized
        var app: ChargebackApplication? = null
            private set
    }
}