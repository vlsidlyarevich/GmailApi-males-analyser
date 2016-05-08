package com.mail.hunter.application.business.services;

import com.mail.hunter.application.business.models.Cost;
import com.mail.hunter.application.business.models.Value;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vlad on 06.05.16.
 */
public final class CostService {


    public static Cost parseSteam(String steamCost){

        Pattern pattern = Pattern.compile("[0-9\\.\\s]+");
        Matcher matcher = pattern.matcher(steamCost);
        Double amount = 0.0;

        if(matcher.find()) {
            amount = Double.valueOf(matcher.group());
        }

        if(steamCost.contains("USD")){
            return new Cost(Value.USD,amount);
        }
        else{
            return new Cost(Value.RUB,amount);
        }
    }

    public static Cost parseOrigin(String originCost){

        Pattern pattern = Pattern.compile("[0-9\\.\\s]+");
        Matcher matcher = pattern.matcher(originCost);
        Double amount = 0.0;

        if(matcher.find()) {
            amount = Double.valueOf(matcher.group());
        }
        if(originCost.contains("$")){
            return new Cost(Value.USD,amount);
        }
        else{
            return new Cost(Value.RUB,amount);
        }
    }

    public static Cost parseOz(String ozCost){

        Pattern pattern = Pattern.compile("[0-9\\.\\s]+");
        Matcher matcher = pattern.matcher(ozCost);
        Double amount = 0.0;

        if(matcher.find()) {
            amount = Double.valueOf(matcher.group().replaceAll("\\s",""));
        }
        if(ozCost.contains("$")){
            return new Cost(Value.USD,amount);
        }
        else{
            return new Cost(Value.BYR,amount);
        }
    }

}
