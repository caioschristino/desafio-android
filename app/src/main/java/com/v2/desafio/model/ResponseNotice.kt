package com.v2.desafio.model

import com.google.gson.annotations.SerializedName

/**
 * Created by csanchez on 24/04/2018.
 */

class ResponseNotice {
    val title: String? = null
    val description: String? = null
    var links: Links? = null
    @SerializedName("primary_action")
    val primaryAction: NoticeAction? = null
    @SerializedName("secondary_action")
    val secondaryAction: NoticeAction? = null
}