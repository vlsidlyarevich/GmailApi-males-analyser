package com.mail.hunter.application.business.controllerBeans;

import com.mail.hunter.application.business.models.Item;
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
import java.text.DecimalFormat;
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
    private boolean checked;
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

        checked = false;
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

        checked = false;
    }

    public void checkCustomers() throws IOException, DecoderException {

        onlinePurchases.clear();
        ozByMessages.clear();
        steamMessages.clear();
        originMessages.clear();

        if (!AuthorizationBeanImpl.authorized) {
            AuthorizationBeanImpl.authorize();
        }

        progress = 0;

        progress += 20;

        List<MessageModel> messages = AuthorizationBeanImpl.getMessages();

        progress += 30;

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
            if (onlinePurchases.size() > 0) {
                checked = true;
            }
        }

        progress += 50;

        if (onlinePurchases.size() != 0)
            root = service.createNode(onlinePurchases);

    }

    public void reset() {
        this.originCheck = false;
        this.steamCheck = false;
        this.ozByCheck = false;
        checked = false;
        onlinePurchases.clear();
        ozByMessages.clear();
        steamMessages.clear();
        originMessages.clear();
        progress = 0;
    }


    public Integer getProgress() {
        if (progress > 100) {
            progress = 100;
        }
        return progress;
    }

    public String calculateOzTotal() {

        Double result = 0.0;
        for (OnlinePurchase onlinePurchase : this.ozByMessages) {
            result += onlinePurchase.getSummary().getAmount();
        }

        return result + " BYR";
    }

    public Integer calculateOzItems() {

        Integer result = 0;
        for (OnlinePurchase onlinePurchase : this.ozByMessages) {
            for (Item item : onlinePurchase.getItems()) {
                result += item.getIntAmount();
            }
        }

        return result;
    }


    public String calculateSteamTotal() {

        Double result = 0.0;
        for (OnlinePurchase onlinePurchase : this.steamMessages) {
            result += onlinePurchase.getSummary().getAmount();
        }

        return result + " USD";
    }

    public Integer calculateSteamItems() {

        Integer result = 0;
        for (OnlinePurchase onlinePurchase : this.steamMessages) {
            for (Item item : onlinePurchase.getItems()) {
                result += item.getIntAmount();
            }
        }

        return result;
    }


    public String calculateOriginTotal() {

        Double result = 0.0;
        for (OnlinePurchase onlinePurchase : this.originMessages) {
            result += onlinePurchase.getSummary().getAmount();
        }

        return result + " RUB";
    }

    public Integer calculateOriginItems() {

        Integer result = 0;
        for (OnlinePurchase onlinePurchase : this.originMessages) {
            for (Item item : onlinePurchase.getItems()) {
                result += item.getIntAmount();
            }
        }

        return result;
    }

    public Integer calculateTotalItems() {
        Integer result = 0;
        if (steamMessages.size() > 0)
            result += this.calculateSteamItems();
        if (originMessages.size() > 0)
            result += this.calculateOriginItems();
        if (ozByMessages.size() > 0)
            result += this.calculateOzItems();

        return result;
    }

    public Integer calculateTotalPurchases() {
        return this.ozByMessages.size() +
                this.originMessages.size() +
                this.steamMessages.size();
    }

    public String calculateTotal(){
        double result = 0.0;
        if(steamCheck){
            for(OnlinePurchase onlinePurchase : steamMessages){
                result+=onlinePurchase.getSummary().getAmount()*
                        onlinePurchase.getSummary().getValue().getAmount();
            }
        }
        if(originCheck){
            for(OnlinePurchase onlinePurchase : originMessages){
                result+=onlinePurchase.getSummary().getAmount()*
                        onlinePurchase.getSummary().getValue().getAmount();
            }
        }
        if(ozByCheck){
            for(OnlinePurchase onlinePurchase : ozByMessages){
                result+=onlinePurchase.getSummary().getAmount()*
                        onlinePurchase.getSummary().getValue().getAmount();
            }
        }

        return (new DecimalFormat("#.##")).format(result)+" USD";
    }


    public boolean isChecked() {
        return checked;
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
