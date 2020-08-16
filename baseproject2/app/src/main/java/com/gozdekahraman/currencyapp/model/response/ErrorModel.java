package com.gozdekahraman.currencyapp.model.response;

/**
 * Created by bekir on 02/08/16.
 */
public class ErrorModel {

    private int code;
    private String message;

    public ErrorModel() {
    }

    public ErrorModel(String error) {
        this.message = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}