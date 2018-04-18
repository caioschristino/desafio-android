package com.v2.desafionubank.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by CaioSChristino on 15/04/18.
 */

public class ResponseChargeback {
    private boolean autoblock;
    private String id;
    private String title;
    private Links links;
    @SerializedName("comment_hint")
    private String hint;
    @SerializedName("reason_details")
    private List<ReasonDetails> details;

    public boolean getAutoblock() {
        return autoblock;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Links getLinks() {
        return links;
    }

    public String getHint() {
        return hint;
    }

    public List<ReasonDetails> getDetails() {
        return details;
    }
}
