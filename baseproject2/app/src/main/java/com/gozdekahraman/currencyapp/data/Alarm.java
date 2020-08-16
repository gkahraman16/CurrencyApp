package com.gozdekahraman.currencyapp.data;

public class Alarm {

    private String name;
    private String currency;
    private String value;

    public Alarm(String name, String currency, String value) {
        this.name = name;
        this.currency = currency;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
