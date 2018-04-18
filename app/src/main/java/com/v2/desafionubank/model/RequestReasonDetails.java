package com.v2.desafionubank.model;

/**
 * Created by CaioSChristino on 18/04/18.
 */

public class RequestReasonDetails {
    private String id;
    private boolean response;

    public RequestReasonDetails(ReasonDetails reasonDetails) {
        this.id = reasonDetails.getId();
        this.response = reasonDetails.isSelected();
    }
}
