package com.mail.hunter.application.business.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Item {


    private Cost cost;
    private String name;
    private String amount;

    public Item(Cost cost, String name) {
        this.cost = cost;
        this.name = name;
        this.amount = "1";
    }

    public Item(Cost cost, String name, String amount) {
        this.cost = cost;
        this.name = name;
        this.amount = amount;
    }

    public Cost getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }

    public Integer getIntAmount(){
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(this.amount);

        if(matcher.find()){
            return Integer.valueOf(matcher.group());
        }
        return 0;
    }
}
