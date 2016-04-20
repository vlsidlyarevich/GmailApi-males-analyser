package com.mailHunter.gmail.Client;


import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;
import com.mailHunter.gmail.Service.Gmail.GmailServices;
import com.mailHunter.gmail.Service.Utils.MessageUtils;
import org.apache.commons.codec.DecoderException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class MailHunter {

    private static final String APPLICATION_NAME =
            "Gmail API Java Quickstart";
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
            System.getProperty("user.home"), ".credentials/gmail-java-quickstart.json");
    private static FileDataStoreFactory DATA_STORE_FACTORY;
    private static final JsonFactory JSON_FACTORY =
            JacksonFactory.getDefaultInstance();
    private static HttpTransport HTTP_TRANSPORT;
    private static final List<String> SCOPES =
            Arrays.asList(GmailScopes.GMAIL_READONLY);


    public static void main(String[] args) throws IOException, DecoderException {

        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
        Gmail service = GmailServices.getGmailService(JSON_FACTORY,HTTP_TRANSPORT,DATA_STORE_FACTORY,SCOPES
                ,DATA_STORE_DIR,APPLICATION_NAME);

        // Print the mails in the user's account
        String user = "me";
        ListMessagesResponse listResponse =
                service.users().messages().list(user).execute();
        List<Message> messages = listResponse.getMessages();
        if (messages.size() == 0) {
            System.out.println("No messages found.");
        } else {
            System.out.println("Messages:");
            for (Message message : messages) {
               MessageUtils.parseMessage(GmailServices.getMessage(service, user, message.getId()));
            }
        }
    }
}
