package com.mail.hunter.application.authorization;

import com.mail.hunter.gmail.models.MessageModel;
import org.apache.commons.codec.DecoderException;

import java.io.IOException;
import java.util.List;

/**
 * Created by vlad on 27.04.16.
 */
public interface AuthorizationBean {

    public void authorize() throws IOException, DecoderException;
    public List<MessageModel> getMessages();
}
