package com.v2.desafionubank.model

import com.google.gson.annotations.SerializedName


/**
 * Created by CaioSChristino on 18/04/18.
 */

class Links {
    val notice: LinkHref? = null
    val self: LinkHref? = null
    val chargeback: LinkHref? = null
    @SerializedName("block_card")
    val blockCard: LinkHref? = null
    @SerializedName("unblock_card")
    val unblockCard: LinkHref? = null
}