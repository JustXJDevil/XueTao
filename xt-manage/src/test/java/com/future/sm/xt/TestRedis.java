package com.future.sm.xt;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import redis.clients.jedis.Jedis;

/*
* 利用SpringBoot的测试规则时才会添加测试注解
*/
public class TestRedis {
    private Jedis jedis;

    /**
     * 当@Test方法执行时先执行这个方法
     * @BeaforeAll不能有返回值
     */
   @Before
    public void init(){
        jedis = new Jedis("192.168.95.128",6379);
    }
    /**
     * spring连接redis入门案例
     */
    @Test
    public void testString(){
        jedis.set("test01","v1");
        System.out.println(jedis.get("test01"));
        jedis.close();
    }

    @Test
    public void testString2(){
//        if (!jedis.exists("test02")){
//            jedis.set("test02","v2");
//            System.out.println("success");
//        }else
//            System.out.println("failed");
        System.out.println(jedis.setnx("test03", "v3"));
    }
}
