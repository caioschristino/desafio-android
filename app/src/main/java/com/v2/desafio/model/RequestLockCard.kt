package com.v2.desafio.model

import java.util.*

/**
 * Created by CaioSChristino on 18/04/18.
 */

class RequestLockCard(private val comment: String,
                           private val _details: List<ReasonDetails>) {
    val reason_details = ArrayList<RequestReasonDetails>()

    init {
        for (detail in _details) {
            reason_details.add(RequestReasonDetails(detail))
        }
    }
}