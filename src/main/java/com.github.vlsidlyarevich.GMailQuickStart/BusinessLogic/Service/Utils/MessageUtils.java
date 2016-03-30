package com.github.vlsidlyarevich.GMailQuickStart.BusinessLogic.Service.Utils;

import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePartHeader;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;


public final class MessageUtils {

    private MessageUtils() {
    }


    public static String parseMessage(Message message) throws DecoderException, UnsupportedEncodingException {


        com.github.vlsidlyarevich.GMailQuickStart.BusinessLogic.Models.Message message1 = new com.github.vlsidlyarevich.GMailQuickStart.BusinessLogic.Models.Message();
        message1.setId(message.getId());
        message1.setLabels(message.getLabelIds());
        message1.setSnippet(message.getSnippet());

        for (MessagePartHeader part : message.getPayload().getHeaders()) {
            if (part.getName().equals("Received")) message1.setReceivingDate(part.getValue());
            if (part.getName().equals("From")) message1.setSender(part.getValue());
            if (part.getName().equals("Date")) message1.setDate(part.getValue());
        }

        for (int i = 0;i< message.getPayload().getParts().size();i++) {

            byte[] decodedBytes = Base64.decodeBase64(message.getPayload().getParts().get(i)
                    .getBody().getData().replace('-', '+').replace('_', '/').getBytes(StandardCharsets.UTF_8));
            if(i == 0){
                message1.setBody(new String(decodedBytes,"UTF-8"));
            }
            if(i == 1){
                message1.setHtmlBody(new String(decodedBytes,"UTF-8"));
            }
        }
        return null;
    }
}
