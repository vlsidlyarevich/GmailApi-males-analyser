package com.mail.hunter.application.business.models;

/**
 * Created by vlad on 06.05.16.
 */
public enum Value {

    BYR(1/19000,"руб."),
    RUB(1/300,"руб."),
    USD(1,"$"),
    UNDEFINED(0,"");

    Value(double amount,String symbol) {
        this.amount = amount;
        this.symbol = symbol;
    }

    private double amount;

    private String symbol;

    public double getAmount() {
        return amount;
    }

    public String getSymbol() {
        return symbol;
    }
}
