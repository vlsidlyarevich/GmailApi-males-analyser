package com.mail.hunter.application.business.models;


import java.util.List;

public class OnlinePurchase {


    private String date;
    private List<Item> items;
    private String summary;
    private String customer;

    public OnlinePurchase(String date, List<Item> items,String summary,String customer) {
        this.summary = summary;
        this.date = date;
        this.items = items;
        this.customer = customer;
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

    public String getCustomer() {
        return customer;
    }
}
