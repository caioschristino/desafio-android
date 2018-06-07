package com.v2.desafio.model

/**
 * Created by CaioSChristino on 18/04/18.
 */

class NoticeAction constructor(private val _title: String? = null,
                               private val _action: String? = null) {
    val title: String? = _title
    val action: String? = _action
}