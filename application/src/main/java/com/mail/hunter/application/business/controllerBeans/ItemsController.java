package com.mail.hunter.application.business.controllerBeans;

import com.mail.hunter.application.business.models.OnlinePurchase;
import com.mail.hunter.application.business.parsers.Parser;
import com.mail.hunter.application.business.parsers.impl.OriginParser;
import com.mail.hunter.application.business.parsers.impl.OzByParser;
import com.mail.hunter.application.business.parsers.impl.SteamParser;
import com.mail.hunter.application.security.authorization.impl.AuthorizationBeanImpl;
import com.mail.hunter.application.view.services.TreeTableService;
import com.mail.hunter.gmail.models.MessageModel;
import org.apache.commons.codec.DecoderException;
import org.primefaces.model.TreeNode;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
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

    private TreeNode root;

    private TreeTableService service;


    private boolean steamCheck;
    private boolean originCheck;
    private boolean ozByCheck;
    private List<OnlinePurchase> steamMessages;
    private List<OnlinePurchase> originMessages;
    private List<OnlinePurchase> ozByMessages;

    private ArrayList<OnlinePurchase> onlinePurchases;

    private Integer progress;

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

        service = new TreeTableService();

        progress = 0;
    }


    @PostConstruct
    public void init() throws IOException, DecoderException {
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

        service = new TreeTableService();

        progress = 0;
    }

    public void checkCustomers() throws IOException, DecoderException {

        if(!AuthorizationBeanImpl.authorized){
            AuthorizationBeanImpl.authorize();
        }

        progress+=20;

        List<MessageModel> messages = AuthorizationBeanImpl.getMessages();

        progress+=30;

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

        progress+=50;

        if(onlinePurchases.size() != 0)
            root = service.createNode(onlinePurchases);

        System.out.println("----------");
    }

    public Integer getProgress() {
        if(progress>100){
            progress = 100;
        }
        return progress;
    }

    public TreeNode getRoot() {
        return root;
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
