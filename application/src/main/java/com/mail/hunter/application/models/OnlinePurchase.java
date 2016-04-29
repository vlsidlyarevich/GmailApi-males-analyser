package com.mail.hunter.application.models;


import java.util.List;

public class OnlinePurchase {


    private String date;
    private List<Item> items;
    private String summary;

    public OnlinePurchase(String date, List<Item> items,String summary) {
        this.summary = summary;
        this.date = date;
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public String getDate() {
        return date;
    }

    public String getSummary() {
        return summary;
    }
}
