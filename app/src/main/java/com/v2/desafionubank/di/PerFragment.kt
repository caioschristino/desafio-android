package com.v2.desafionubank.di

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

/**
 * Created by csanchez on 19/04/2018.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
annotation class PerFragment