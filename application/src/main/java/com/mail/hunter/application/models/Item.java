package com.mail.hunter.application.models;

public class Item {


    private String cost;
    private String name;

    public Item(String cost, String name) {
        this.cost = cost;
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }
}
