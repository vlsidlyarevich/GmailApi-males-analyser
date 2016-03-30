package com.github.vlsidlyarevich.GMailQuickStart.BusinessLogic.Models;


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
    private Boolean bodyView;

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
        bodyView = false;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getId() {
        return id;
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
        return receivingDate;
    }

    public void setReceivingDate(String receivingDate) {
        this.receivingDate = receivingDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getHtmlBody() {
        return htmlBody;
    }

    public void setHtmlBody(String htmlBody) {
        this.htmlBody = htmlBody;
    }

    public Boolean getBodyView() {
        return bodyView;
    }

    public void setBodyView(Boolean bodyView) {
        this.bodyView = bodyView;
    }
}
