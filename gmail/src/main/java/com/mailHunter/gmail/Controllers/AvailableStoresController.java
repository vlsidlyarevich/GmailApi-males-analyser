package com.mailHunter.gmail.Controllers;


import com.google.api.services.gmail.model.Message;
import org.apache.commons.codec.binary.Base64;

import javax.faces.bean.ManagedBean;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@ManagedBean
public class AvailableStoresController {

    private boolean steamCheck;
    private boolean aliExpressCheck;
    private boolean originCheck;

    public AvailableStoresController(){
        steamCheck = false;
        aliExpressCheck = false;
        originCheck = false;
    }

    public void checkCustomers(ArrayList<Message> messages) throws UnsupportedEncodingException {

        for(Message message :messages) {
            String html = message.getPayload().getParts().get(0).getParts().get(1).getBody().getData();

            byte[] decodedBytes = Base64.decodeBase64(html.replace('-', '+').replace('_', '/').getBytes(StandardCharsets.UTF_8));
            System.out.printf(new String(decodedBytes, "UTF-8"));

            /*
            Document doc = Jsoup.parse(message.getPayload().getParts());*/
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
