package com.mail.hunter.application.models;


import java.util.List;

public class OnlinePurchase {


    private String date;
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public OnlinePurchase(String date, List<Item> items) {

        this.date = date;
        this.items = items;
    }

    public String getDate() {
        return date;
    }
}
