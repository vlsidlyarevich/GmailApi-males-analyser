package com.mail.hunter.application.business.parsers.impl;


import com.mail.hunter.application.business.models.Customer;
import com.mail.hunter.application.business.models.Item;
import com.mail.hunter.application.business.models.OnlinePurchase;
import com.mail.hunter.application.business.parsers.Parser;
import com.mail.hunter.application.business.services.CostService;
import com.mail.hunter.gmail.models.MessageModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class OriginParser implements Parser {


    public OnlinePurchase parseMessage(MessageModel messageModel) {

        try {

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
            items.add(new Item(CostService.parseOrigin(costValue), productName));


            return new OnlinePurchase(dateValue, items, CostService.parseOrigin(costValue),
                    Customer.ORIGIN);

        }catch (Exception e){
            System.out.println(e.toString());
        }
        return null;
    }


}
