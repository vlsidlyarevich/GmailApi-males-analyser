package com.mailHunter.gmail.Models;


import java.util.List;

public class MessageModel {

    private String sender;
    private String id;
    private List<String> labels;
    private String receivingDate;
    private String date;
    private String snippet;
    private String body;
    private String htmlBody;

    public MessageModel() {
    }

    public MessageModel(String sender, String id, List<String> labels, String receivingDate, String date, String snippet, String body, String htmlBody) {
        this.sender = sender;
        this.id = id;
        this.labels = labels;
        this.receivingDate = receivingDate;
        this.date = date;
        this.snippet = snippet;
        this.body = body;
        this.htmlBody = htmlBody;
        bodyView = false;
    }


    public String getSender() {
        if(sender!=null)
        return sender;
        else return "sender unknown";
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getId() {
        if(id!=null)
        return id;
        else return "id unknown";
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public String getReceivingDate() {
        if(receivingDate!=null)
        return receivingDate;
        else return "unknown receiving date";
    }

    public void setReceivingDate(String receivingDate) {
        this.receivingDate = receivingDate;
    }

    public String getDate() {
        if(date!=null)
        return date;
        else return "date unknown";
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSnippet() {
        if(snippet!=null)
        return snippet;
        else return "";
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getBody() {
        if(body!=null)
        return body;
        else return "";
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getHtmlBody() {
        if(htmlBody!=null)
        return htmlBody;
        else return "";
    }

    public void setHtmlBody(String htmlBody) {
        this.htmlBody = htmlBody;
    }
}
