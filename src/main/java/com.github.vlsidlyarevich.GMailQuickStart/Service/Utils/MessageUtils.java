package com.github.vlsidlyarevich.GMailQuickStart.Service.Utils;

import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePart;
import com.google.api.services.gmail.model.MessagePartHeader;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * Created by Администратор on 23.03.2016.
 */
public final class MessageUtils {

    private MessageUtils(){
    }


    public static String parseMessage(Message message) throws DecoderException, UnsupportedEncodingException {

        for (MessagePart part : message.getPayload().getParts()) {

            byte[] decodedBytes = Base64.decodeBase64(part.getBody().getData().replace('-', '+').replace('_', '/').getBytes(StandardCharsets.UTF_8));
            //System.out.println( new String(decodedBytes,"UTF-8"));

        }
        com.github.vlsidlyarevich.GMailQuickStart.Models.Message message1 = new com.github.vlsidlyarevich.GMailQuickStart.Models.Message();
        message1.setId(message.getId());
        message1.setLabels(message.getLabelIds());
        message1.setSnippet(message.getSnippet());

        for(MessagePartHeader part: message.getPayload().getHeaders()){
           if(part.getName().equals("Received")) message1.setReceivingDate(part.getValue());
            if(part.getName().equals("From")) message1.setSender(part.getValue());
            if(part.getName().equals("Date")) message1.setDate(part.getValue());
        }
        System.out.println();
        return null;
    }
}
