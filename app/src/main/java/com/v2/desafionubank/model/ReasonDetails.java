package com.v2.desafionubank.model;

/**
 * Created by CaioSChristino on 15/04/18.
 */

public class ReasonDetails {
    private String id;

    private String title;

    private boolean selected;

    public String getId ()
    {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }
}
