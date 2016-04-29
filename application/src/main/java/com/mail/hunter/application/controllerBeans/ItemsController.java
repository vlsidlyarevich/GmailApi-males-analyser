package com.mail.hunter.application.controllerBeans;

import com.mail.hunter.application.authorization.impl.AuthorizationBeanImpl;
import com.mail.hunter.application.models.OnlinePurchase;
import com.mail.hunter.application.parsers.Parser;
import com.mail.hunter.gmail.models.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlad on 29.04.16.
 */
@RequestScoped
@ManagedBean(name = "itemsController")
public class ItemsController {

    @Autowired
    @Qualifier(value = "originParser")
    private Parser originParser;

    @Autowired
    @Qualifier(value = "ozByParser")
    private Parser ozByParser;

    @Autowired
    @Qualifier(value = "steamParser")
    private Parser steamParser;



    private boolean steamCheck;
    private boolean originCheck;
    private boolean ozByCheck;
    private List<MessageModel> steamMessages;
    private List<MessageModel> originMessages;
    private List<MessageModel> ozByMessages;

    private ArrayList<OnlinePurchase> onlinePurchases;

    public ItemsController(){
        steamCheck = false;
        originCheck = false;
        ozByCheck = false;
        steamMessages = new ArrayList<>();
        originMessages = new ArrayList<>();
        ozByMessages = new ArrayList<>();
    }

    @PostConstruct
    public void init(){
        steamCheck = false;
        originCheck = false;
        ozByCheck = false;
        steamMessages = new ArrayList<>();
        originMessages = new ArrayList<>();
        ozByMessages = new ArrayList<>();
    }

    public void checkCustomers() throws UnsupportedEncodingException {

        List<MessageModel> messages = AuthorizationBeanImpl.getMessages();

        for(MessageModel message :messages) {

            if(steamCheck && message.getHtmlBody().contains("noreply@steampowered.com")){
                steamMessages.add(message);
            }
            if(originCheck && message.getHtmlBody().contains("noreply@ea.com")){
                originMessages.add(message);
            }
            if(ozByCheck && message.getHtmlBody().contains("oz@oz.by")){
                ozByMessages.add(message);
            }
        }
    }


    public boolean isOzByCheck() {
        return ozByCheck;
    }

    public void setOzByCheck(boolean ozByCheck) {
        this.ozByCheck = ozByCheck;
    }

    public boolean isOriginCheck() {
        return originCheck;
    }

    public void setOriginCheck(boolean originCheck) {
        this.originCheck = originCheck;
    }

    public boolean isSteamCheck() {
        return steamCheck;
    }

    public void setSteamCheck(boolean steamCheck) {
        this.steamCheck = steamCheck;
    }

    public List<MessageModel> getSteamMessages() {
        return steamMessages;
    }

    public List<MessageModel> getOriginMessages() {
        return originMessages;
    }

    public List<MessageModel> getOzByMessages() {
        return ozByMessages;
    }

    public ArrayList<OnlinePurchase> getOnlinePurchases() {
        return onlinePurchases;
    }
}
