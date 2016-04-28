package com.mail.hunter.application.controllerBeans;


import com.mail.hunter.application.authorization.impl.AuthorizationBeanImpl;
import com.mail.hunter.gmail.models.MessageModel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


@ManagedBean
public class AvailableStoresController {

    private boolean steamCheck;
    private boolean aliExpressCheck;
    private boolean originCheck;
    private boolean ozByCheck;
    private List<MessageModel> steamMessages;
    private List<MessageModel> aliExpressMessages;
    private List<MessageModel> originMessages;
    private List<MessageModel> ozByMessages;


    public AvailableStoresController(){
        steamCheck = false;
        aliExpressCheck = false;
        originCheck = false;
        ozByCheck = false;
        steamMessages = new ArrayList<>();
        aliExpressMessages = new ArrayList<>();
        originMessages = new ArrayList<>();
        ozByMessages = new ArrayList<>();
    }

    @PostConstruct
    public void init(){
        steamCheck = false;
        aliExpressCheck = false;
        originCheck = false;
        ozByCheck = false;
        steamMessages = new ArrayList<>();
        aliExpressMessages = new ArrayList<>();
        originMessages = new ArrayList<>();
        ozByMessages = new ArrayList<>();
    }
    public void checkCustomers() throws UnsupportedEncodingException {

        List<MessageModel> messages = AuthorizationBeanImpl.getMessages();

        for(MessageModel message :messages) {

            if(message.getHtmlBody().contains("noreply@steampowered.com")){
                steamCheck = true;
                steamMessages.add(message);
            }
            if(message.getHtmlBody().contains("noreply@ea.com")){
                originCheck = true;
                originMessages.add(message);
            }
            if(message.getHtmlBody().contains("oz@oz.by")){
                ozByCheck = true;
                ozByMessages.add(message);
            }
        }
    }



    public boolean isSteamCheck() {
        return steamCheck;
    }

    public void setSteamCheck(boolean steamCheck) {
        this.steamCheck = steamCheck;
    }

    public boolean isAliExpressCheck() {
        return aliExpressCheck;
    }

    public void setAliExpressCheck(boolean aliExpressCheck) {
        this.aliExpressCheck = aliExpressCheck;
    }

    public boolean isOriginCheck() {
        return originCheck;
    }

    public void setOriginCheck(boolean originCheck) {
        this.originCheck = originCheck;
    }
}