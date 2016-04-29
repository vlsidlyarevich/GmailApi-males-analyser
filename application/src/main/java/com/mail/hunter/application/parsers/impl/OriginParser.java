package com.mail.hunter.application.parsers.impl;


import com.mail.hunter.application.models.Item;
import com.mail.hunter.application.models.OnlinePurchase;
import com.mail.hunter.application.parsers.Parser;
import com.mail.hunter.gmail.models.MessageModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component("originParser")
public class OriginParser implements Parser {


    public OnlinePurchase parseMessage(MessageModel messageModel){


        Document doc = Jsoup.parse(messageModel.getHtmlBody());

        Elements outerTable = doc.select("table");

        Elements productAndCostStrongs = outerTable.get(6).select("strong");
        Elements dateStrongs = outerTable.get(3).select("strong");

        Element product = productAndCostStrongs.get(0);
        Element cost = productAndCostStrongs.get(1);
        Element date = dateStrongs.get(2);

        String productName = product.select("span").first().text();
        String costValue = cost.select("span").first().text();
        String dateValue = date.text();

        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(costValue,productName));


        return new OnlinePurchase(dateValue,items,costValue);
    }



}
