package com.gozdekahraman.currencyapp.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Currency {
    private String code;
    private String alis;
    private String satis;
    private String tarih;
    private String dusuk;
    private String yuksek;
    private String kapanis;

    public Currency(String code, String purchase_price, String sale_price, String date, String low, String high, String last) {
        this.code = code;
        this.alis = purchase_price;
        this.satis = sale_price;
        this.tarih = date;
        this.dusuk = low;
        this.yuksek = high;
        this.kapanis = last;
    }

    public String getCode() {
        return code;
    }

    public String getAlis() {
        return alis;
    }

    public String getSatis() { return satis; }

    public String getTarih() {
        String[] history = this.tarih.split(" ");
        String saat = history[1].substring(0,5);
        this.tarih = saat;
        return tarih; }

    public String getDusuk() {
        return dusuk;
    }

    public String getYuksek() { return yuksek; }

    public String getKapanis() {
        return kapanis;
    }

}
