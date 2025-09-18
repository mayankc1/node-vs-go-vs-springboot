package com.example.shorten;

import java.io.Serializable;

public class UrlShortenerError implements Serializable {
    private String errMsg;

    public UrlShortenerError() {
        
    }

    public UrlShortenerError(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}