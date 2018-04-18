package com.v2.desafionubank.model;

/**
 * Created by csanchez on 17/04/2018.
 */

public class ResponsePost {
    private String status;
    private boolean block;

    public String getStatus() {
        return status;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public boolean isBlock() {
        return block;
    }
}
