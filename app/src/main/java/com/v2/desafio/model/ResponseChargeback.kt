package com.v2.desafio.model

import com.google.gson.annotations.SerializedName

/**
 * Created by CaioSChristino on 18/04/18.
 */

class ResponseChargeback {
    val autoblock: Boolean = false
    val id: String? = null
    val title: String? = null
    val links: Links? = null
    @SerializedName("comment_hint")
    val hint: String? = null
    @SerializedName("reason_details")
    val details: MutableList<ReasonDetails>? = null
}