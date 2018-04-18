package com.v2.desafionubank.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by CaioSChristino on 20/02/2018.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentContext {
}