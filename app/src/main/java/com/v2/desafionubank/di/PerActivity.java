package com.v2.desafionubank.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by CaioSChristino on 05/02/18.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {

}