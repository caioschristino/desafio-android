package com.v2.desafionubank.model

import java.util.*

/**
 * Created by CaioSChristino on 18/04/18.
 */

class ResponseChargeback {
    val autoblock: Boolean = false
    val id: String? = null
    val title: String? = null
    val links: Links? = null
    val hint: String? = null
    val details: ArrayList<ReasonDetails>? = null
}