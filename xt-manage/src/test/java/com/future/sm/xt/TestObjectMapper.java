package com.future.sm.xt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.future.sm.xt.pojo.ItemDesc;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TestObjectMapper {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    /**
     * 实现对象和JSON的转化
     */
    @Test
    public void object2Json() throws JsonProcessingException {
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(1000L);
        itemDesc.setItemDesc("test");
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(itemDesc.getCreated());
        //1.将对象转化成JSON
        String json = MAPPER.writeValueAsString(itemDesc);
        System.out.println(json);

        //2.将json复原成对象
        ItemDesc itemDesc1 = MAPPER.readValue(json,ItemDesc.class);
        System.out.println(itemDesc1);
    }

    /**
     * 实现list集合的转换
     */
    @Test
    public void list2JSON() throws JsonProcessingException {
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(1000L);
        itemDesc.setItemDesc("test");
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(itemDesc.getCreated());
        ItemDesc itemDesc1 = new ItemDesc();
        itemDesc.setItemId(1000L);
        itemDesc.setItemDesc("test");
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(itemDesc.getCreated());

        List<ItemDesc> list = new ArrayList<>();
        list.add(itemDesc);
        list.add(itemDesc1);
        //1.list->json
        String json = MAPPER.writeValueAsString(list);
        System.out.println(json);
        //2.json->list
        List list1 = MAPPER.readValue(json, List.class);
        System.out.println(list1);

    }

}
