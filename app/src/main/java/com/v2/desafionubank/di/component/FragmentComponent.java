package com.v2.desafionubank.di.component;

import com.v2.desafionubank.di.PerFragment;
import com.v2.desafionubank.di.module.FragmentModule;
import com.v2.desafionubank.ui.BaseFragment;

import dagger.Component;

/**
 * Created by CaioSChristino on 20/02/2018.
 */

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(BaseFragment mFragment);
}