package com.mail.hunter.application.models;

public class Item {


    private String cost;
    private String name;
    private String amount;

    public Item(String cost, String name) {
        this.cost = cost;
        this.name = name;
        amount = "1";
    }

    public Item(String cost, String name, String amount) {
        this.cost = cost;
        this.name = name;
        this.amount = amount;
    }

    public String getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }
}
