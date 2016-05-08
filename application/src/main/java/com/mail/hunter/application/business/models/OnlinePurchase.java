package com.mail.hunter.application.business.models;


import java.io.Serializable;
import java.util.List;

public class OnlinePurchase implements Serializable,Comparable<OnlinePurchase>  {


    private String date;
    private List<Item> items;
    private Cost summary;
    private Customer customer;

    public OnlinePurchase(String date, List<Item> items, Cost summary, Customer customer) {
        this.date = date;
        this.items = items;
        this.summary = summary;
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OnlinePurchase that = (OnlinePurchase) o;

        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (!items.equals(that.items)) return false;
        if (summary != null ? !summary.equals(that.summary) : that.summary != null) return false;
        return customer == that.customer;

    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + items.hashCode();
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + customer.hashCode();
        return result;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Item> getItems() {
        return items;
    }

    public String getDate() {
        return date;
    }

    public Cost getSummary() {
        return summary;
    }


    @Override
    public int compareTo(OnlinePurchase o) {
        return date.compareTo(o.getDate());
    }
}
