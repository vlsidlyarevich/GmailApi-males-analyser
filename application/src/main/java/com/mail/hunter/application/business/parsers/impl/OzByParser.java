package com.mail.hunter.application.business.parsers.impl;


import com.mail.hunter.application.business.models.*;
import com.mail.hunter.application.business.parsers.Parser;
import com.mail.hunter.application.business.services.CostService;
import com.mail.hunter.gmail.models.MessageModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class OzByParser implements Parser {

    public OnlinePurchase parseMessage(MessageModel messageModel) {

        try {
            Document doc = Jsoup.parse(messageModel.getHtmlBody());

            Element outerTable = doc.select("table").get(2);

            Element productRows = outerTable.select("tr").get(2);

            ArrayList<Item> items = new ArrayList<>();

            for (int i = 1; i < productRows.select("tr").get(5).select("tr").size(); i++) {
                String name = productRows.select("tr").get(5).select("tr").get(i).select("td").get(0).text();
                String amount = productRows.select("tr").get(5).select("tr").get(i).select("td").get(1).text();
                items.add(new Item(new Cost(Value.UNDEFINED,0.0), name, amount));
            }

            Element valueRows = outerTable.select("tr").get(26);
            Element innerValueRow = valueRows.select("tr").get(1);

            String costValue = innerValueRow.select("td").get(1).text();
            String dateValue = doc.text().substring(doc.text().indexOf("Дата:") + 5,
                    doc.text().indexOf("Тема")).trim();

            return new OnlinePurchase(dateValue, items, CostService.parseOz(costValue),
                    Customer.OZ);
        }catch (Exception e){
            System.out.println(e.toString());
        }

        return null;
    }
}
