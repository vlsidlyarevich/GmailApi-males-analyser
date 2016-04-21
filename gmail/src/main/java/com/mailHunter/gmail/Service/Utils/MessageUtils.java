package com.mailHunter.gmail.Service.Utils;

import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePartHeader;
import com.mailHunter.gmail.Models.MessageModel;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;


public final class MessageUtils {

    private MessageUtils() {
    }


    public static MessageModel parseMessage(Message message) throws DecoderException, UnsupportedEncodingException {


        MessageModel result = new MessageModel();
        result.setId(message.getId());
        result.setLabels(message.getLabelIds());
        result.setSnippet(message.getSnippet());

        for (MessagePartHeader part : message.getPayload().getHeaders()) {
            if (part.getName().equals("Received")) result.setReceivingDate(part.getValue());
            if (part.getName().equals("From")) result.setSender(part.getValue());
            if (part.getName().equals("Date")) result.setDate(part.getValue());
        }

        for (int i = 0;i< message.getPayload().getParts().size();i++) {

            if(message.getPayload().getParts().get(i).getMimeType().equalsIgnoreCase("text/plain")){

                byte[] decodedBytes = Base64.decodeBase64(message.getPayload().getParts().get(i)
                    .getBody().getData().replace('-', '+').replace('_', '/').getBytes(StandardCharsets.UTF_8));

                result.setBody(new String(decodedBytes, "UTF-8"));
            }
            if(message.getPayload().getParts().get(i).getMimeType().equalsIgnoreCase("text/html")) {
                byte[] decodedBytes = Base64.decodeBase64(message.getPayload().getParts().get(i)
                        .getBody().getData().replace('-', '+').replace('_', '/').getBytes(StandardCharsets.UTF_8));

                result.setHtmlBody(new String(decodedBytes, "UTF-8"));
            }
        }
        return result;
    }
}
