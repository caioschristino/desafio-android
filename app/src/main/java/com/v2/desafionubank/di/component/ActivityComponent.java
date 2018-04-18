package com.v2.desafionubank.di.component;


import com.v2.desafionubank.di.PerActivity;
import com.v2.desafionubank.di.module.ActivityModule;
import com.v2.desafionubank.ui.BaseMain;

import dagger.Component;

/**
 * Created by CaioSChristino on 05/02/18.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(BaseMain mBaseMain);
}