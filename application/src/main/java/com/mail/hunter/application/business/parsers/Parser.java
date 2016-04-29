package com.mail.hunter.application.business.parsers;


import com.mail.hunter.application.business.models.OnlinePurchase;
import com.mail.hunter.gmail.models.MessageModel;

public interface Parser {

    public OnlinePurchase parseMessage(MessageModel messageModel);
}
