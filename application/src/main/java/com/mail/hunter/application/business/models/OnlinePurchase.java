package com.mail.hunter.application.business.models;


import java.io.Serializable;
import java.util.List;

public class OnlinePurchase implements Serializable,Comparable<OnlinePurchase>  {


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OnlinePurchase that = (OnlinePurchase) o;

        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (!items.equals(that.items)) return false;
        if (summary != null ? !summary.equals(that.summary) : that.summary != null) return false;
        return customer != null ? customer.equals(that.customer) : that.customer == null;

    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + items.hashCode();
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(OnlinePurchase o) {
        return this.customer.compareTo(o.getCustomer());
    }
}
