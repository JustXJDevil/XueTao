package com.future.sm.xt.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.future.sm.xt.exception.ServiceException;
import com.future.sm.xt.mapper.UserMapper;
import com.future.sm.xt.pojo.User;
import com.future.sm.xt.service.DubboUserService;
import com.future.sm.xt.util.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import redis.clients.jedis.JedisCluster;

import java.util.UUID;

@Service
public class DubboUserServiceImpl implements DubboUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JedisCluster jedisCluster;


    @Override
    public void saveUser(User user) {
        //为了避免数据库报错，暂时使用phone代替email
        user.setEmail(user.getPhone());
        //密码加密入库（MD5）
        String MD5Pwd = DigestUtils.md5DigestAsHex((user.getPassword()).getBytes());
        user.setPassword(MD5Pwd);

        int row = userMapper.saveUser(user);
        if (row == 0)
            throw new ServiceException("新增用户失败");
    }

    /*
    * 1. 返回值数据： 返回加密后的密钥
    * 2. 校检用户信息是否有效，如果无效返回null
    * 3. 如果密码正确，将用户信息保存到redis中
    * */
    @Override
    public String findUserByUI(User user, String ip) {

        //1. 进行校检
        user.setPassword(DigestUtils.md5DigestAsHex((user.getPassword()).getBytes()));
        User findUser = userMapper.findUser(user);
        if (findUser == null) {
            return null;
        }
        //2.动态生成uuid
        String uuid = UUID.randomUUID().toString();
        String ticket = DigestUtils.md5DigestAsHex(uuid.getBytes());
        user.setPassword("你猜猜看");//不在redis中显示真实密码
        //4. 保存到redis
        jedisCluster.hset(ticket,"XT_USER", ObjectMapperUtil.toJSON(user));
        jedisCluster.hset(ticket,"XT_USER_IP",ip);
        jedisCluster.expire(ticket,3600*24*7);

        return ticket;
    }
}
