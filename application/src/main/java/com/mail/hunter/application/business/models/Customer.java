package com.mail.hunter.application.business.models;

/**
 * Created by vlad on 05.05.16.
 */
public enum Customer {

    ORIGIN("www.origin.com"),
    STEAM("store.steampowered.com"),
    OZ("oz.by");


    Customer(String string){
        this.domain = string;
    }

    private String domain;

    public String getDomain() {
        return domain;
    }
}
