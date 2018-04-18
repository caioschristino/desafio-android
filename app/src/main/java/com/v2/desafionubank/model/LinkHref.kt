package com.v2.desafionubank.model

/**
 * Created by CaioSChristino on 18/04/18.
 */

data class LinkHref constructor(private val _href: String) {
    val href: String = _href
        get() {
            return field.toString();
        }
}