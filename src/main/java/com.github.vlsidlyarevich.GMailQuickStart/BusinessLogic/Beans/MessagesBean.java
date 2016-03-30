package com.github.vlsidlyarevich.GMailQuickStart.BusinessLogic.Beans;


import com.github.vlsidlyarevich.GMailQuickStart.BusinessLogic.Models.Message;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@ViewScoped
@ManagedBean
public class MessagesBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Message> messages;
    private int currentPage;
    private int pageItems;


    @PostConstruct
    public void init(){

        currentPage = 0;
        pageItems = 5;
        messages = new LinkedList<Message>();

        Message message = new Message();
        message.setBody("test body 1");
        message.setId("1234132");
        message.setSnippet("test header");
        message.setBodyView(false);

        Message message1 = new Message();
        message1.setBody("test body 2");
        message1.setId("12135432");
        message1.setSnippet("test header 2");
        message1.setBodyView(false);

        Message message2 = new Message();
        message2.setBody("test body 3");
        message2.setId("1265432");
        message2.setSnippet("test header 3");
        message2.setBodyView(false);

        Message message3 = new Message();
        message3.setBody("test body 4");
        message3.setId("12612342");
        message3.setSnippet("test header 4");
        message3.setBodyView(false);

        Message message5 = new Message();
        message5.setBody("test body 5");
        message5.setId("1222222");
        message5.setSnippet("test header 5");
        message5.setBodyView(false);

        Message message6 = new Message();
        message6.setBody("test body 6");
        message6.setId("12614352");
        message6.setSnippet("test header 6");
        message6.setBodyView(false);

        messages.add(message3);
        messages.add(message1);
        messages.add(message2);
        messages.add(message6);
        messages.add(message5);
        messages.add(message);
    }

    public List<Message> getSublist() {

        int startPosition = currentPage * pageItems;
        int endPosition = startPosition + pageItems;


        if (endPosition > messages.size()) {
            endPosition = messages.size();
        }
        return messages.subList(startPosition, endPosition);
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

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
