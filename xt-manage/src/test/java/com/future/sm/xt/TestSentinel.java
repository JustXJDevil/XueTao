package com.future.sm.xt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * 测试哨兵模式
 *
 * 端口说明
 * redis默认端口:6379
 * 通信端口(心跳检测):16379
 * 哨兵默认端口:26379
 */
public class TestSentinel {

    @Test
    public void test01(){
        Set set = new HashSet();
        set.add("192.168.95.128:26379");
        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool(
                "mymaster",
                set
        );

        Jedis jedis = jedisSentinelPool.getResource();
        jedis.set("testSentinel01","value01");
        System.out.println(jedis.get("testSentinel01"));
    }
}
