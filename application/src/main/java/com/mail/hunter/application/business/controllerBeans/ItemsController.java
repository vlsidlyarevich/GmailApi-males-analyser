package com.mail.hunter.application.business.controllerBeans;

import com.mail.hunter.application.business.models.OnlinePurchase;
import com.mail.hunter.application.business.parsers.Parser;
import com.mail.hunter.application.business.parsers.impl.OriginParser;
import com.mail.hunter.application.business.parsers.impl.OzByParser;
import com.mail.hunter.application.business.parsers.impl.SteamParser;
import com.mail.hunter.application.security.authorization.impl.AuthorizationBeanImpl;
import com.mail.hunter.gmail.models.MessageModel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlad on 29.04.16.
 */
@SessionScoped
@ManagedBean(name = "itemsController")
public class ItemsController {

    private Parser originParser;
    private Parser ozByParser;
    private Parser steamParser;


    private boolean steamCheck;
    private boolean originCheck;
    private boolean ozByCheck;
    private List<OnlinePurchase> steamMessages;
    private List<OnlinePurchase> originMessages;
    private List<OnlinePurchase> ozByMessages;

    private ArrayList<OnlinePurchase> onlinePurchases;

    public ItemsController() {
        steamCheck = false;
        originCheck = false;
        ozByCheck = false;
        steamMessages = new ArrayList<>();
        originMessages = new ArrayList<>();
        ozByMessages = new ArrayList<>();
        onlinePurchases = new ArrayList<>();

        originParser = new OriginParser();
        steamParser = new SteamParser();
        ozByParser = new OzByParser();
    }


    @PostConstruct
    public void init() {
        steamCheck = false;
        originCheck = false;
        ozByCheck = false;
        steamMessages = new ArrayList<>();
        originMessages = new ArrayList<>();
        ozByMessages = new ArrayList<>();
        onlinePurchases = new ArrayList<>();

        originParser = new OriginParser();
        steamParser = new SteamParser();
        ozByParser = new OzByParser();
    }

    public void checkCustomers() throws UnsupportedEncodingException {

        List<MessageModel> messages = AuthorizationBeanImpl.getMessages();

        for (MessageModel message : messages) {

            if (steamCheck && message.getHtmlBody() != null && message.getHtmlBody().contains("noreply@steampowered.com")) {
                OnlinePurchase onlinePurchase = steamParser.parseMessage(message);
                if (onlinePurchase != null) {
                    onlinePurchases.add(onlinePurchase);
                    steamMessages.add(onlinePurchase);
                }
            }
            if (originCheck && message.getHtmlBody() != null && message.getHtmlBody().contains("noreply@ea.com")) {
                OnlinePurchase onlinePurchase = originParser.parseMessage(message);
                if (onlinePurchase != null) {
                    onlinePurchases.add(onlinePurchase);
                    originMessages.add(onlinePurchase);
                }
            }
            if (ozByCheck && message.getHtmlBody() != null && message.getHtmlBody().contains("oz@oz.by")) {
                OnlinePurchase onlinePurchase = ozByParser.parseMessage(message);
                if (onlinePurchase != null) {
                    onlinePurchases.add(onlinePurchase);
                    ozByMessages.add(onlinePurchase);
                }
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

    public List<OnlinePurchase> getSteamMessages() {
        return steamMessages;
    }

    public List<OnlinePurchase> getOriginMessages() {
        return originMessages;
    }

    public List<OnlinePurchase> getOzByMessages() {
        return ozByMessages;
    }

    public ArrayList<OnlinePurchase> getOnlinePurchases() {
        return onlinePurchases;
    }
}
