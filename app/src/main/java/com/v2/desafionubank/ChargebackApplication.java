package com.v2.desafionubank;

import android.app.Application;

import com.v2.desafionubank.di.component.ApplicationComponent;
import com.v2.desafionubank.di.component.DaggerApplicationComponent;
import com.v2.desafionubank.di.module.ApplicationModule;

/**
 * Created by CaioSChristino on 24/03/18.
 */

public class ChargebackApplication extends Application {
    private RxBus mbRxBus;
    protected ApplicationComponent applicationComponent;
    private static ChargebackApplication instance;


    public ChargebackApplication() {
        instance = this;
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public static synchronized ChargebackApplication getApp() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mbRxBus = new RxBus();
        instance = this;
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }

    public RxBus getBus() {
        return mbRxBus;
    }
}