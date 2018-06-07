package com.v2.desafio.model

/**
 * Created by CaioSChristino on 18/04/18.
 */

class RequestReasonDetails(val reasonDetails: ReasonDetails) {
    private val id: String?
    private val response: Boolean

    init {
        this.id = reasonDetails.id
        this.response = reasonDetails.isSelected!!
    }
}