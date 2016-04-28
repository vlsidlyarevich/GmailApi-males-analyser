package com.mail.hunter.application.parsers.impl;


import com.mail.hunter.application.models.Item;
import com.mail.hunter.application.models.OnlinePurchase;
import com.mail.hunter.application.parsers.Parser;
import com.mail.hunter.gmail.models.MessageModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class SteamParser implements Parser {


    public OnlinePurchase parseMessage(MessageModel messageModel){


        Document doc = Jsoup.parse(messageModel.getHtmlBody());

        Elements table = doc.select("table");

        Elements rows = table.select("tr");

        String productName = rows.get(0).select("div").text();
        String productCost = rows.get(2).select("td").get(1).text();
        String date = rows.get(10).select("td").get(1).text();


        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(productCost,productName));


        return new OnlinePurchase(date,items);
    }


}
