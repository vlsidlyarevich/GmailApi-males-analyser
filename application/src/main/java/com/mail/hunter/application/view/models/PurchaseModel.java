package com.mail.hunter.application.view.models;

/**
 * Created by vlad on 02.05.16.
 */
public class PurchaseModel {

    private String customer;
    private String date;
    private String summary;
    private String name;

    public PurchaseModel(String customer, String date, String summary, String name) {
        this.customer = customer;
        this.date = date;
        this.summary = summary;
        this.name = name;
    }

    public String getCustomer() {
        return customer;
    }

    public String getDate() {
        return date;
    }

    public String getSummary() {
        return summary;
    }

    public String getName() {
        return name;
    }
}
