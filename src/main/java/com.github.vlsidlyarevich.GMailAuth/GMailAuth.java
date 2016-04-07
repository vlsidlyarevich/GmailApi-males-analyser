package com.github.vlsidlyarevich.GMailAuth;


import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePart;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class GMailAuth {

    /** Application name. */
    private static final String APPLICATION_NAME =
            "Gmail API Java Quickstart";
    private static final String CLIENT_ID=
            "xxx";
    private static final String CLIENT_SECRET=
            "xxx";
    private static GoogleAuthorizationCodeFlow flow;
    private static final List<String> SCOPES =
            Arrays.asList(GmailScopes.GMAIL_READONLY);
    private static FileDataStoreFactory dataStoreFactory;

    public static void main(String[] args) throws IOException, DecoderException {
        JsonFactory jsonFactory = new JacksonFactory();
        HttpTransport httpTransport = new NetHttpTransport();
        dataStoreFactory = new FileDataStoreFactory(new java.io.File(
                System.getProperty("user.home"), ".credentials/gmail-java-quickstart.json"));


        flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport,
                jsonFactory,CLIENT_ID,CLIENT_SECRET,SCOPES).setDataStoreFactory(dataStoreFactory).build();

        String code = flow.newAuthorizationUrl().setRedirectUri("http://localhost").build();
        //тут мы просим токен чтобы потом достучаться до информации пользователя
        GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
                httpTransport,jsonFactory,
                CLIENT_ID,CLIENT_SECRET, code,"postmessage").execute();



    }




    public static Message getMessage(Gmail service,String user,String messageId) throws IOException {

        return service.users().messages().get(user,messageId).execute();

    }


    public static String base64UrlDecode(Message message) throws DecoderException, UnsupportedEncodingException {

        for (MessagePart part : message.getPayload().getParts()) {

            byte[] decodedBytes = Base64.decodeBase64(part.getBody().getData().replace('-', '+').replace('_', '/').getBytes(StandardCharsets.UTF_8));
            System.out.println( new String(decodedBytes,"UTF-8"));

        }
        return null;
    }

}
