package com.v2.desafionubank.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CaioSChristino on 10/04/18.
 */

public class ResponseNotice {
    private String title;
    private String description;
    private Links links;
    @SerializedName("primary_action")
    private NoticeAction primaryAction;
    @SerializedName("secondary_action")
    private NoticeAction secondaryAction;


    public ResponseNotice() {
    }

    public ResponseNotice(Links links) {
        this.links = links;
    }

    public Links getLinks() {
        return links;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public NoticeAction getPrimaryAction() {
        return primaryAction;
    }

    public NoticeAction getSecondaryAction() {
        return secondaryAction;
    }
}
