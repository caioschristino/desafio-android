package com.v2.desafionubank.model;

/**
 * Created by CaioSChristino on 15/04/18.
 */

public class ResponseError {
    private String error;

    public ResponseError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
