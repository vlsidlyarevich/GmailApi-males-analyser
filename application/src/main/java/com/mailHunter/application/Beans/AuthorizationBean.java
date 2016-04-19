package com.mailHunter.application.Beans;


import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;
import com.mailHunter.gmail.Models.MessageModel;
import com.mailHunter.gmail.Service.Gmail.GmailServices;
import com.mailHunter.gmail.Service.Utils.MessageUtils;
import org.apache.commons.codec.DecoderException;

import javax.faces.bean.ManagedBean;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


@ManagedBean
public class AuthorizationBean {

    private static final String APPLICATION_NAME =
            "Mail Hunter";
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
            System.getProperty("user.home"), ".credentials/mail-hunter.json");
    private static FileDataStoreFactory DATA_STORE_FACTORY;
    private static final JsonFactory JSON_FACTORY =
            JacksonFactory.getDefaultInstance();
    private static HttpTransport HTTP_TRANSPORT;
    private static final List<String> SCOPES =
            Arrays.asList(GmailScopes.GMAIL_READONLY);
    private static Gmail service;


    public static void authorize() throws IOException, DecoderException {


        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }


        service = GmailServices.getGmailService(JSON_FACTORY, HTTP_TRANSPORT, DATA_STORE_FACTORY, SCOPES
                , DATA_STORE_DIR, APPLICATION_NAME);

    }

    public static List<MessageModel> getMessages() {

        String user = "me";

        ListMessagesResponse listResponse =
                null;
        try {
            listResponse = service.users().messages().list(user).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Message> messages = listResponse.getMessages();
        List<MessageModel> models =
                new LinkedList<MessageModel>();


        if (messages.size() != 0) {
            for (Message message : messages) {
                try {

                    models.add(MessageUtils.parseMessage(GmailServices.getMessage(service, user, message.getId())));

                } catch (DecoderException | IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return models;
    }



}

