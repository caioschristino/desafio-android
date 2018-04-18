package com.v2.desafionubank.model

/**
 * Created by CaioSChristino on 18/04/18.
 */


class ReasonDetails constructor(private val _id: String? = null,
                                private val _title: String? = null,
                                private val _isSelected: Boolean? = null) {
    val id: String? = _id
    val title: String? = _title
    var isSelected: Boolean? = _isSelected
}
