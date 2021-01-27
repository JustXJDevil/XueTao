package com.future.sm.xt;

import org.junit.Test;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试redis的分片
 */
public class TestShards {

    @Test
    public void test01(){
        List<JedisShardInfo> list = new ArrayList();
        list.add(new JedisShardInfo("192.168.95.128",6379));
        list.add(new JedisShardInfo("192.168.95.128",6380));
        list.add(new JedisShardInfo("192.168.95.128",6381));
        ShardedJedis shardedJedis = new ShardedJedis(list);

        //shardedJedis的操作和Jedis相同
        shardedJedis.set("testShardedJedis01","v1");
        System.out.println(shardedJedis.get("testShardedJedis01"));
    }
}
