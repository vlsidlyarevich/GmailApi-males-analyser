package com.mailHunter.gmail.Service.Gmail;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import com.mailHunter.gmail.Client.MailHunter;
import com.mailHunter.gmail.Service.Authorization.AuthorizeUtils;

import java.io.IOException;
import java.util.List;


public final class GmailServices {

    private GmailServices(){
    }

    /**
     * Build and return an authorized Gmail client service.
     * @return an authorized Gmail client service
     * @throws IOException
     */
    public static Gmail getGmailService(JsonFactory JSON_FACTORY,
                                        HttpTransport HTTP_TRANSPORT,FileDataStoreFactory DATA_STORE_FACTORY,
                                        List<String> SCOPES,java.io.File DATA_STORE_DIR,String APPLICATION_NAME)
                                        throws IOException {

        Credential credential = AuthorizeUtils.authorize(MailHunter.class, JSON_FACTORY, HTTP_TRANSPORT, DATA_STORE_FACTORY,
                SCOPES, DATA_STORE_DIR);
        return new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }


    public static Message getMessage(Gmail service,String user,String messageId) throws IOException {

        return service.users().messages().get(user,messageId).execute();

    }
}
