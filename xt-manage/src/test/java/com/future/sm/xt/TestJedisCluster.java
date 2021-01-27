package com.future.sm.xt;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * 测试redis集群
 */
public class TestJedisCluster {

    @Test
    public void test01(){
        Set<HostAndPort> set = new HashSet<>();
        set.add(new HostAndPort("192.168.95.128",7000));
        set.add(new HostAndPort("192.168.95.128",7001));
        set.add(new HostAndPort("192.168.95.128",7002));
        set.add(new HostAndPort("192.168.95.128",7003));
        set.add(new HostAndPort("192.168.95.128",7004));
        set.add(new HostAndPort("192.168.95.128",7005));

        JedisCluster jedisCluster = new JedisCluster(set);

        jedisCluster.set("testCluster01","cluster01");
        System.out.println(jedisCluster.get("testCluster01"));
    }
}
