package com.v2.desafionubank.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CaioSChristino on 10/04/18.
 */

public class Links {
    private LinkHref notice;
    private LinkHref self;
    private LinkHref chargeback;
    @SerializedName("block_card")
    private LinkHref blockCard;
    @SerializedName("unblock_card")
    private LinkHref unblockCard;

    public LinkHref getNotice() {
        return notice;
    }

    public LinkHref getSelf() {
        return self;
    }

    public LinkHref getBlockCard() {
        return blockCard;
    }

    public LinkHref getUnblockCard() {
        return unblockCard;
    }

    public LinkHref getChargeback() {
        return chargeback;
    }
}
