package com.github.vlsidlyarevich.GMailQuickStart.Service.Utils;

import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePart;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * Created by Администратор on 23.03.2016.
 */
public final class DecodeUtils {

    private DecodeUtils(){
    }


    public static String base64UrlDecode(Message message) throws DecoderException, UnsupportedEncodingException {

        for (MessagePart part : message.getPayload().getParts()) {

            byte[] decodedBytes = Base64.decodeBase64(part.getBody().getData().replace('-', '+').replace('_', '/').getBytes(StandardCharsets.UTF_8));
            System.out.println( new String(decodedBytes,"UTF-8"));

        }
        return null;
    }
}
