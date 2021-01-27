package com.future.sm.xt.aop;

import com.future.sm.xt.annotation.CacheFind;
import com.future.sm.xt.util.ObjectMapperUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.ShardedJedis;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

@Aspect
@Component
public class CacheAspect {
//    @Autowired
//    private ShardedJedis jedis;
//    @Resource(name = "poolJedis")
//    private Jedis jedis;
    //懒加载
    @Autowired(required = false)
    private JedisCluster jedis;

    @Pointcut("@annotation(com.future.sm.xt.annotation.CacheFind)")
    public void cachePointcut(){}

    @Around("cachePointcut()")
    public Object around(ProceedingJoinPoint joinPoint){
        CacheFind cacheFind = ((MethodSignature)joinPoint.getSignature())
                .getMethod().getAnnotation(CacheFind.class);
        String key = getKey(joinPoint,cacheFind);
            if (!jedis.exists(key)){
                try {
                    Object obj = joinPoint.proceed();
                    //判断用户是否设置了过期时间
                    if (cacheFind.seconds() != 0){
                        jedis.setex(key,cacheFind.seconds(),ObjectMapperUtil.toJSON(obj));
                    }else
                        jedis.set(key,ObjectMapperUtil.toJSON(obj));
                    return obj;
                } catch (Throwable t) {
                    t.printStackTrace();
                    throw new RuntimeException(t);
                }
            }else {
                System.out.println("走缓存");
                return ObjectMapperUtil.toObject(
                        jedis.get(key),
                        getType(joinPoint));
            }
    }

    //判断用户是否传递参数, 如果用户传参使用用户自己的key.
    //如果用户没有指定参数,使用动态生成的.
    private String getKey(ProceedingJoinPoint joinPoint,CacheFind cacheFind){
        MethodSignature methodSignature =
                (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringTypeName();
        String methodName = methodSignature.getName();
        Long parameter = (Long) joinPoint.getArgs()[0];

        String userKey = cacheFind.key();
        if (userKey.equals("")){
            return className+"."+methodName+"::"+parameter;
        }else
            return className+"."+methodName+"::"+userKey;
    }

    private Class<?> getType(ProceedingJoinPoint joinPoint){
        MethodSignature methodSignature =
                (MethodSignature)joinPoint.getSignature();
        return methodSignature.getReturnType();
    }


}
