package com.future.sm.xt.mapper;

import com.future.sm.xt.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("select * from tb_user where ${type} = #{param}")
    User check(@Param("param") String param, @Param("type") String type);

    @Select("select * from tb_user where email = #{param}")
    User checkEmail(String param);
    @Select("select * from tb_user where phone = #{param}")
    User checkPhone(String param);
    @Select("select * from tb_user where username = #{param}")
    User checkUsername(String param);

    int saveUser(User user);

    @Select("select * from tb_user where username=#{username} and password=#{password} ")
    User findUser(User user);
}
