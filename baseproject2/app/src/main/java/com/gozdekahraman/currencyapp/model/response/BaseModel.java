package com.gozdekahraman.currencyapp.model.response;

/**
 * Created by bekir on 15/08/16.
 */
public class BaseModel<T> {

    private String message;
    private boolean error;
    private T data;

    public BaseModel() {
    }

    public BaseModel(T data) {
        this.data = data;
    }

    public String getErrorDescription() {
        return message;
    }

    public String getMessage() {
        return message;
    }

    public boolean isError() {
        return error;
    }

    public T getData() {
        return data;
    }

}
