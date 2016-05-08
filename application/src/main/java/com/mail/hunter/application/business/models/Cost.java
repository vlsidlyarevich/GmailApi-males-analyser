package com.mail.hunter.application.business.models;

/**
 * Created by vlad on 06.05.16.
 */
public class Cost {

    private Value value;
    private Double amount;

    public Cost(Value value, Double amount) {
        this.value = value;
        this.amount = amount;
    }

    public Value getValue() {
        return value;
    }

    public Double getAmount() {
        return amount;
    }

    public String getStringAmount(){
        if(this.value == Value.RUB)
            return amount+" руб.";
        if(this.value == Value.BYR)
            return amount+" руб.";
        if(this.value == Value.USD)
            return amount+" usd";
        return amount.toString();
    }
}
