package com.mail.hunter.application.beans;


import com.mail.hunter.gmail.models.MessageModel;
import org.apache.commons.codec.DecoderException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@ViewScoped
@ManagedBean
public class MessagesBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<MessageModel> messageModels;
    private int currentPage;
    private int pageItems;


    @PostConstruct
    public void init(){

        currentPage = 0;
        pageItems = 5;
        messageModels = new LinkedList<MessageModel>();


        try {
            AuthorizationBean.authorize();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DecoderException e) {
            e.printStackTrace();
        }

        messageModels = AuthorizationBean.getMessages();
    }

    public List<MessageModel> getSublist() {

        int startPosition = currentPage * pageItems;
        int endPosition = startPosition + pageItems;


        if (endPosition > messageModels.size()) {
            endPosition = messageModels.size();
        }
        return messageModels.subList(startPosition, endPosition);
    }

    public void action(AjaxBehaviorEvent event) throws javax.faces.event.AbortProcessingException {
        currentPage = (Integer) event.getComponent().getAttributes().get("index") - 1;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageItems() {
        return pageItems;
    }

    public void setPageItems(int pageItems) {
        this.pageItems = pageItems;
    }

    public List<MessageModel> getMessageModels() {
        return messageModels;
    }

    public void setMessageModels(List<MessageModel> messageModels) {
        this.messageModels = messageModels;
    }
}
