package com.v2.desafionubank.model


/**
 * Created by CaioSChristino on 18/04/18.
 */

data class Links constructor(private val _notice: LinkHref? = null,
                             private val _self: LinkHref? = null,
                             private val _chargeback: LinkHref? = null,
                             private val _block_card: LinkHref? = null,
                             private val _unblock_card: LinkHref? = null) {
    val notice: LinkHref? = _notice
    val chargeback: LinkHref? = _chargeback
    val self: LinkHref? = _self
    val blockCard: LinkHref? = _block_card
    val unblockCard: LinkHref? = _unblock_card
}

