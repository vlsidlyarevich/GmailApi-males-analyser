package com.mail.hunter.application.business.parsers.impl;


import com.mail.hunter.application.business.models.Item;
import com.mail.hunter.application.business.models.OnlinePurchase;
import com.mail.hunter.application.business.parsers.Parser;
import com.mail.hunter.gmail.models.MessageModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class SteamParser implements Parser {


    public OnlinePurchase parseMessage(MessageModel messageModel) {

        try {

            Document doc = Jsoup.parse(messageModel.getHtmlBody());

            Elements table = doc.select("table");

            Elements rows = table.select("tr");

            String productName = rows.get(0).select("div").text();
            String costValue = rows.get(2).select("td").get(1).text();
            String dateValue = rows.get(10).select("td").get(1).text();


            ArrayList<Item> items = new ArrayList<>();
            items.add(new Item(costValue, productName));


            return new OnlinePurchase(dateValue, items, costValue,"store.steampowered.com");
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        return null;
    }


}
