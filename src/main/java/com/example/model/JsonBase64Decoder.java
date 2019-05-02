package com.example.model;

import java.io.IOException;
import java.util.Base64;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * @author Paulo Henrique Heidemann
 * Class responsible for decoding the Base64 encoded String
 */
public class JsonBase64Decoder extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
        return Base64.getEncoder().encodeToString(parser.getText().getBytes());
    }
    
}