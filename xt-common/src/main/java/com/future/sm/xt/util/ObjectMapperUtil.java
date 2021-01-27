package com.future.sm.xt.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 对象<-互转->JSON
 */
public class ObjectMapperUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String toJSON(Object obj){
        String json = null;
        try {
            json = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return json;
    }

    public static <T> T toObject(String json,Class<T> target){
        T t = null;
        try {
            t = objectMapper.readValue(json,target);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return t;
    }
}
