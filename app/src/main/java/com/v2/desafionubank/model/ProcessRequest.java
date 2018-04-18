package com.v2.desafionubank.model;

/**
 * Created by CaioSChristino on 15/04/18.
 */

public class ProcessRequest {
    private boolean showProcess;

    public ProcessRequest(boolean showProcess) {
        this.showProcess = showProcess;
    }

    public boolean isShowProcess() {
        return showProcess;
    }
}
