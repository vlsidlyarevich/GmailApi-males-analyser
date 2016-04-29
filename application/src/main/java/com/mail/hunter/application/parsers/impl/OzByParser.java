package com.mail.hunter.application.parsers.impl;


import com.mail.hunter.application.models.Item;
import com.mail.hunter.application.models.OnlinePurchase;
import com.mail.hunter.application.parsers.Parser;
import com.mail.hunter.gmail.models.MessageModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class OzByParser implements Parser {

    public OnlinePurchase parseMessage(MessageModel messageModel) {


        Document doc = Jsoup.parse(messageModel.getHtmlBody());

        Element outerTable = doc.select("table").get(2);

        Element productRows = outerTable.select("tr").get(2);

        ArrayList<Item> items = new ArrayList<>();

        for (int i = 1; i < productRows.select("tr").get(5).select("tr").size(); i++) {
            String name = productRows.select("tr").get(5).select("tr").get(i).select("td").get(0).text();
            String amount = productRows.select("tr").get(5).select("tr").get(i).select("td").get(1).text();
            items.add(new Item("unknown",name,amount));
        }

        System.out.println();
        return null;
    }


}
