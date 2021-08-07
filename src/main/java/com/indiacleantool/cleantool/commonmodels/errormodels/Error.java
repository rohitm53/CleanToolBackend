package com.indiacleantool.cleantool.commonmodels.errormodels;

public class Error {

    private String errorMsg;


    public Error(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
