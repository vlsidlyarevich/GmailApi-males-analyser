package com.github.vlsidlyarevich.GMailQuickStart.BusinesLogic.Models;


import java.util.List;

public class Message {

    private String sender;
    private String id;
    private List<String> labels;
    private String receivingDate;
    private String date;
    private String snippet;
    private String body;
    private String htmlBody;

    public Message() {
    }

    public Message(String sender, String id, List<String> labels, String receivingDate, String date, String snippet, String body, String htmlBody) {
        this.sender = sender;
        this.id = id;
        this.labels = labels;
        this.receivingDate = receivingDate;
        this.date = date;
        this.snippet = snippet;
        this.body = body;
        this.htmlBody = htmlBody;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public void setReceivingDate(String receivingDate) {
        this.receivingDate = receivingDate;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setHtmlBody(String htmlBody) {
        this.htmlBody = htmlBody;
    }
}
