package com.v2.desafionubank.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by csanchez on 17/04/2018.
 */

public class RequestLockCard {
    private String comment;

    private List<RequestReasonDetails> reason_details = new ArrayList<>();

    public RequestLockCard(String comment, List<ReasonDetails> details) {
        this.comment = comment;

        for (ReasonDetails detail : details) {
            reason_details.add(new RequestReasonDetails(detail));
        }
    }
}
