package com.mail.hunter.application.business.models;

/**
 * Created by vlad on 06.05.16.
 */
public enum Value {

    BYR((1.0/19000),"руб."),
    RUB((1.0/300),"руб."),
    USD(1.0,"$"),
    UNDEFINED(0.0,"");

    Value(Double amount,String symbol) {
        this.amount = amount;
        this.symbol = symbol;
    }

    private Double amount;

    private String symbol;

    public Double getAmount() {
        return amount;
    }

    public String getSymbol() {
        return symbol;
    }
}
