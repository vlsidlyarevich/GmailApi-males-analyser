package com.mail.hunter.application.parsers;


import com.mail.hunter.application.models.OnlinePurchase;
import com.mail.hunter.gmail.models.MessageModel;

public interface Parser {

    public OnlinePurchase parseMessage(MessageModel messageModel);
}
