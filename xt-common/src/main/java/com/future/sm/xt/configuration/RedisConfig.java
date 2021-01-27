package com.future.sm.xt.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import redis.clients.jedis.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@PropertySource("classpath:/properties/redis.properties")
public class RedisConfig {
    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private int port;
    @Value("${redis.nodes}")
    private String hostAndType;
    @Value("${redis.sentinels}")
    private String sentinels;

    @Bean("jedis")
    @Scope("prototype")//设置为多例,当用户使用时创建
    public Jedis newJedis(){
        return new Jedis(host,port);
    }

    //分片
    @Bean
    @Scope("prototype")
    public ShardedJedis newShardedJedis(){
        String[] info = hostAndType.split(",");
        List<JedisShardInfo> shardInfos = new ArrayList<>();
        for (String str:info){
            String host = str.split(":")[0];
            int port = Integer.parseInt(str.split(":")[1]);
            shardInfos.add(new JedisShardInfo(host,port));
        }
        return new ShardedJedis(shardInfos);
    }

    /**
     * 哨兵池
     */
//    @Bean
//    public JedisSentinelPool newJedisSentinelPool(){
//        Set set = new HashSet();
//        set.add(sentinels);
//        JedisSentinelPool jedisSentinelPool =
//                new JedisSentinelPool("mymaster",set);
//        return jedisSentinelPool;
//    }

    /**哨兵池里的jedis*/
    @Bean
    @Scope("prototype")
    public Jedis poolJedis(
            @Autowired JedisSentinelPool jedisSentinelPool
    ){
        return jedisSentinelPool.getResource();
    }

    @Value("${redis.clusterNodes}")
    private String clusterNodes;
    /**
     * redis集群
     */
    @Bean
    @Scope("prototype")
    public JedisCluster newJedisCluster(@Qualifier("clusterSet") Set clusterSet){
        //为了更快,交给集合管理
//        String[] hostAndPorts= clusterNodes.split(",");
//        Set<HostAndPort> set = new HashSet<>();
//        for (String hostAndPort:hostAndPorts) {
//            set.add(new HostAndPort(
//                    (hostAndPort.split(":"))[0],
//                    Integer.parseInt(hostAndPort.split(":")[1])));
//        }
        return new JedisCluster(clusterSet);
    }

    @Bean
    public Set<HostAndPort> clusterSet(){
        String[] hostAndPorts= clusterNodes.split(",");
        Set<HostAndPort> set = new HashSet<>();//为了更快,交给集合管理
        for (String hostAndPort:hostAndPorts) {
            set.add(new HostAndPort(
                    (hostAndPort.split(":"))[0],
                    Integer.parseInt(hostAndPort.split(":")[1])));
        }
        return set;
    }

}
